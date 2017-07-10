#include "includes.h"
#include <sys/stat.h>
#include <arpa/inet.h>
#include <netdb.h>

#define MAXPENDING 5    /* Maximum outstanding connection requests */

/* Terminates the Program with an Error Message */
void Die(char *errorMessage);

/* Terminates the Program and closes
 * Close the socket passed as parameter
 */
void DieWithError(char *errorMessage, int sock); 

/* Handle the request */
size_t HandleHTTPRequest(const char *wr, char *clntIP, int clntSocket, int mdbSocket);

int main(int argc, char *argv[])
{
    int servSock;                     /* Socket descriptor for server */
    int clntSock;                     /* Socket descriptor for client */
    int mdbSock;                      /* Socket descriptor for mdb-server */
    struct sockaddr_in HTTPServAddr;  /* Local address */
    struct sockaddr_in HTTPClntAddr;  /* Client address */
    struct sockaddr_in MDBServAddr;   /* MDB Serv address */
    struct hostent *he;               /* Server IP */
    unsigned short HTTPServPort;      /* Server port */
    unsigned short MDBServPort;       /* MDBServer port */
    char *webroot;
    char *mdbIP;
    unsigned int clntLen;             /* Length of client address data structure */

    if (signal(SIGPIPE, SIG_IGN) == SIG_ERR)
        Die("signal() failed");

    if (argc != 5)     /* Test for correct number of arguments */
    {
        fprintf(stderr, "Usage:  %s <server_port> <web_root> <mdb-lookup-host> <mdb-lookup-port>\n", argv[0]);
        exit(1);
    }

    /* Where the files will be served from */ 
    webroot = argv[2];

    /* Get server address */
    if ((he = gethostbyname(argv[3])) == NULL) {
        Die("gethostbyname failed");
    }

    mdbIP = inet_ntoa(*(struct in_addr *) he->h_addr);

    /* Create a socket for the MDB Server */
    MDBServPort = atoi(argv[4]);
    if((mdbSock = socket(PF_INET,SOCK_STREAM,IPPROTO_TCP)) < 0)
        Die("socket() failed");
    /* Construct local address structure */
    memset(&MDBServAddr, 0, sizeof(MDBServAddr));   /* Zero out structure */
    MDBServAddr.sin_family = AF_INET;               /* Internet address family */
    MDBServAddr.sin_addr.s_addr = inet_addr(mdbIP); /* Any incoming interface */
    MDBServAddr.sin_port = htons(MDBServPort);      /* MDB Local port */

    /* Connect to the MDB Server */
    if (connect(mdbSock, (struct sockaddr *)&MDBServAddr,sizeof(MDBServAddr)) < 0) {
        Die("Conection to MDB failed");
    }

    HTTPServPort = atoi(argv[1]);  /* First arg:  local port */
    /* Create socket for incoming connections */
    if ((servSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        Die("socket() failed");
      
    /* Construct local address structure */
    memset(&HTTPServAddr, 0, sizeof(HTTPServAddr));   /* Zero out structure */
    HTTPServAddr.sin_family = AF_INET;                /* Internet address family */
    HTTPServAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* Any incoming interface */
    HTTPServAddr.sin_port = htons(HTTPServPort);      /* Local port */

    /* Bind to the local address */
    if (bind(servSock, (struct sockaddr *) &HTTPServAddr, sizeof(HTTPServAddr)) < 0)
        DieWithError("bind() failed", servSock);

    /* Mark the socket so it will listen for incoming connections */
    if (listen(servSock, MAXPENDING) < 0)
        DieWithError("listen() failed", servSock);
    
    for (;;) /* Run forever */
    { 
      
        /* Set the size of the in-out parameter */
        clntLen = sizeof(HTTPClntAddr);

        /* Wait for a client to connect */
        if ((clntSock = accept(servSock, (struct sockaddr *) &HTTPClntAddr, 
                               &clntLen)) < 0)
            DieWithError("accept() failed", clntSock);

        /* clntSock is connected to a client! */
        char *clientIP = inet_ntoa(HTTPClntAddr.sin_addr);
        HandleHTTPRequest(webroot,clientIP,clntSock,mdbSock);


    }
    /* NOT REACHED */
}
