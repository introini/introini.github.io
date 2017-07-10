
#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket(), connect(), send(), and recv() */
#include <arpa/inet.h>  /* for sockaddr_in and inet_addr() */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */
#include <sys/types.h>
#include <netdb.h>

#define DATABUFSIZE 4096

void DieWithError(char *errorMessage);  /* Error handling function */

int main(int argc, char *argv[])
{
    int sock;                             /* Socket descriptor */
    struct sockaddr_in serverAddr;        /* Remote server address */
    struct hostent *he;                   /* Get IP from server name */
    unsigned short serverPort;            /* Remote server port */
    char *serverName, *serverIP;          /* www.something.com and IP addr */
    char getRequest[100];                 /* GET Request Buffer */
    char headerRequest[100];              /* Header Request Buffer */
    char blankLine[3] = {'\r','\n','\0'}; /* Blank Line */
    unsigned int len;                     /* Length of GET request strings */ 
    char dataBuffer[DATABUFSIZE];         /* Buffer for received data */
    char *fileName, *filePath;            /* File path and file name */
    size_t bytesRead;                     /* Bytes Read by fread() */
    char *ok = "200";                     /* Error checking status code */
    char *serverResponse;                 /* Used to store header lines */
     
    /* Test for correct number of arguments */
    if (argc != 4) {
       fprintf(stderr, "usage: %s <Host_Name> <Port> <File_Path>\n",
               argv[0]);
       fprintf(stderr, "   ex) %s www.cs.columbia.edu 80 /index.html\n",
               argv[0]);
       exit(1);
    }

    /* Parse the file name from the path */
    filePath = argv[3];
    if ((fileName = strrchr(filePath, '/')) == NULL) {
       fprintf(stderr, "usage: %s <Host_Name> <Port> <File_Path>\n",
               argv[0]);
       fprintf(stderr, "   ex) %s www.cs.columbia.edu 80 /index.html\n",
               argv[0]);
       exit(1);
    }
  
    /* Force port 80 to be used */
    if ( strcmp(argv[2],"80") != 0)
        fprintf(stderr,  "Bad port: %s - Using port 80 instead.\n", argv[2]);
        serverPort = atoi("80"); 

    /* Getting ip address from hostname */
    serverName = argv[1]; 
    if ((he = gethostbyname(serverName)) == NULL) {
        DieWithError("gethostbyname failed");
    }
    serverIP = inet_ntoa(*(struct in_addr *)he->h_addr);
    
    /* Build requests that should be sent to the server */
    if (sprintf(headerRequest,"Host: %s:%hu",serverName,serverPort) == 0)
        DieWithError("sprintf failed");  
    
    if (sprintf(getRequest,"GET %s HTTP/1.0",filePath) == 0)
        DieWithError("sprintf failed");  
    
    /* Create a reliable, stream socket using TCP */
    if ((sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed");

    /* Construct the serverer address structure */
    memset(&serverAddr, 0, sizeof(serverAddr));     /* Zero out structure */
    serverAddr.sin_family      = AF_INET;             /* Internet address family */
    serverAddr.sin_addr.s_addr = inet_addr(serverIP);   /* serverer IP address */
    serverAddr.sin_port        = htons(serverPort); /* serverer port */

    /* Establish the connection to the echo serverer */
    if (connect(sock, (struct sockaddr *) &serverAddr, sizeof(serverAddr)) < 0)
        DieWithError("connect() failed");

    /* Sending the initial GET request */
    len = strlen(strcat(getRequest,blankLine));
    if (send(sock, getRequest, len, 0) != len)
        DieWithError("send() sent a different number of bytes than expected");

    /* Sending the first header request */
    len = strlen(strcat(headerRequest, blankLine));
    if (send(sock, headerRequest, len, 0) != len)
        DieWithError("send() sent a different number of bytes than expected");

    /* Sending the blank line */
    len = strlen(blankLine);
    if (send(sock, blankLine, len, 0) != len)
        DieWithError("send() sent a different number of bytes than expected");

    /* Open a file descriptor that will serve as our output file
     * 'filename+1' allows us to grab just the file name, without
     * the leading '/' to correctly name our output file.
     */
    FILE *out = fopen(fileName+1, "w+");
    if (out == NULL)
        DieWithError(fileName+1);

    /* Wrap the socket in a file descriptor to use fgets and fread */
    FILE *sd = fdopen(sock,"r");
    if (sd == NULL)
        DieWithError("failed to wrap socket in FILE *");
    
    /* First line recieved from the remote server */
    serverResponse = fgets(dataBuffer, sizeof(dataBuffer), sd);
    
    /* Checking for 200 OK */
    if ( !strstr(serverResponse,ok ) )
        DieWithError(serverResponse);
    
    /* Read the rest of the header response up to and including the 
     * blank line.
     */
    while (strcmp(serverResponse,blankLine) != 0) {
        serverResponse = fgets(dataBuffer, sizeof(dataBuffer), sd);
    }

    /* Read the incoming data in chunks of 4096 bytes
     * and store them in our output file.
     */
    while ((bytesRead = fread(dataBuffer, 1, sizeof(dataBuffer), sd)) > 0) {
        if (fwrite(dataBuffer, 1, bytesRead, out) != bytesRead) 
           DieWithError("fwrite failed");
    } 

    /* Catch any errors that might've came about wile
     * 'out' or 'sd' were open.
     */
    if (ferror(sd))
        DieWithError("fread failed");

    if (ferror(out))
        DieWithError("fwrite failed");

    /* Close out file descriptors */
    close(sock);
    fclose(sd);
    fclose(out);
    exit(0);

}
