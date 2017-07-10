#include "includes.h"

#define MAXPENDING 5    /* Maximum outstanding connection requests */

void DieWithError(char *errorMessage);  /* Error handling function */
void HandleTCPClient(char *database, int clntSocket);   /* TCP client handling function */

int main(int argc, char *argv[])
{
    int servSock;                    /* Socket descriptor for server */
    int clntSock;                    /* Socket descriptor for client */
    struct sockaddr_in mdbServAddr; /* Local address */
    struct sockaddr_in mdbClntAddr; /* Client address */
    unsigned short mdbServPort;     /* Server port */
    unsigned int clntLen;            /* Length of client address data structure */
    char *db = argv[1];
    // ignore SIGPIPE so that we don't terminate when we call
    // send() on a disconnected socket.
    if (signal(SIGPIPE, SIG_IGN) == SIG_ERR) 
        DieWithError("signal() failed");

    if (argc != 3)     /* Test for correct number of arguments */
    {
        fprintf(stderr, "Usage:  %s <Database> <Server Port>\n", argv[0]);
        exit(1);
    }

    mdbServPort = atoi(argv[2]);  /* First arg:  local port */

    /* Create socket for incoming connections */
    if ((servSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed");
      
    /* Construct local address structure */
    memset(&mdbServAddr, 0, sizeof(mdbServAddr));   /* Zero out structure */
    mdbServAddr.sin_family = AF_INET;                /* Internet address family */
    mdbServAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* Any incoming interface */
    mdbServAddr.sin_port = htons(mdbServPort);      /* Local port */

    /* Bind to the local address */
    if (bind(servSock, (struct sockaddr *) &mdbServAddr, sizeof(mdbServAddr)) < 0)
        DieWithError("bind() failed");

    /* Mark the socket so it will listen for incoming connections */
    if (listen(servSock, MAXPENDING) < 0)
        DieWithError("listen() failed");

    for (;;) /* Run forever */
    {
        /* Set the size of the in-out parameter */
        clntLen = sizeof(mdbClntAddr);

        /* Wait for a client to connect */
        if ((clntSock = accept(servSock, (struct sockaddr *) &mdbClntAddr, 
                               &clntLen)) < 0)
            DieWithError("accept() failed");

        /* clntSock is connected to a client! */

        printf("Handling client %s\n", inet_ntoa(mdbClntAddr.sin_addr));

        HandleTCPClient(db,clntSock);
    }
    /* NOT REACHED */
}
