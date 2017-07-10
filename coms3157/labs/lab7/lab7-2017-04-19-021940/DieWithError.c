#include <stdio.h>  /* for perror() */
#include <stdlib.h> /* for exit() */
#include <unistd.h>

void DieWithError(char *errorMessage, int socket)
{
    close(socket);
    fprintf(stderr, "%s\n", errorMessage);
    exit(1);
}
void Die(char *errorMessage) 
{
    fprintf(stderr, "%s\n", errorMessage);
    exit(1);
}
