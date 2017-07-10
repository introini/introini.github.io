/*
 * twecho
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/*
 * We need to capitalize each word as it gets
 * copied over to the duplicate array. So we'll take
 * each letter and capitalize it before it gets copied
 * over.
 */
void strcp(char *t,const char *s)
{
    /* The while loop will return false once
     * it gets to the terminating 0 and exit.
     * In the meantime, each letter from *s get's 
     * capitalized with 'toupper()' and assigned to *t
     * then each increments. 
     */
    while((*t++ = toupper(*s++))) ; 
}
static char **duplicateArgs(int argc, char **argv)
{
    int i; // Index variable
    /* Vertical Array:
     * Need to allocate enough space for each pointer
     * PLUS a terminating zero. So we'll allocate
     * argc+1 space * 8 bytes.
     */
    char **cp = (char **) malloc((argc+1) * sizeof(char *));
    
    /*
     * Check that we're not allocating 0 space. 
     */
    if (cp == NULL)
    {
        perror("malloc failed");
        exit(1);
    }
    /*
     * Initilize each block of space to 0.
     */
    memset(cp,'\0',((argc+1) * sizeof(char *)));

    /*
     * Now we can allocate enough memory for each string
     * we will copy from argv. We will need to loop through
     * 'cp' enough times to make space for each string, but
     * also leave a terminating 0. This will copy the Vertical
     * Array structure.
     */
    for ( i=0 ; i<argc ; i++ )
    {
        /* Here we'll malloc the length of each string + 1. 
         * This gives us enough space for each character + 
         * a terminating 0
         */
        cp[i] = (char *) malloc(strlen(argv[i])+1 * sizeof(char));

       /*
        * Check that we're not allocating 0 space. 
        */
        if (cp[i] == NULL)
        {
            perror("malloc failed");
            exit(1);
        }

       /*
        * Initilize each block of space to 0.
        */
        memset(cp[i],'\0',strlen(argv[i])+1 * sizeof(char));
        
        /*
         * Calling the strcp function will copy each string 
         * over from argv.
         */
        strcp(cp[i],argv[i]);
    }

    return cp;
}

static void freeDuplicatedArgs(char **copy)
{  
  // Save address of copy
  char **p = copy;
  /* Loop through each pointer in copy
   * and free it. Stop when we reach the
   * terminating 0.
   */
  while(*copy != 0)
  {
      free(*copy++);
  }

  // Free copy at its original address.
  free(p);
}

/*
 * DO NOT MODIFY main().
 */
int main(int argc, char **argv)
{
    if (argc <= 1)
        return 1;

    char **copy = duplicateArgs(argc, argv);
    char **p = copy;

    argv++;
    p++;
    while (*argv) {
        printf("%s %s\n", *argv++, *p++);
    }

    freeDuplicatedArgs(copy);

    return 0;
}
