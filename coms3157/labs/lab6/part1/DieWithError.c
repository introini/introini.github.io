#include <stdio.h>  /* for perror() */
#include <stdlib.h> /* for exit() */

void DieWithError(char *errorMessage)
{
    fprintf(stderr, "%s", errorMessage);
    exit(1);
}
