#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <ctype.h>

static void die(const char *s) {
    perror(s);
    exit(1);
}

int main() {

    char buf[7];
    pid_t pid;
   
    /* Port numbers should be between 10000 - 64000 */
    printf("port number: ");
    
    while ( fgets(buf, sizeof(buf), stdin) != NULL ) {

        /* Clean up input by removing any trailing '\n' characters */
        if( buf[strlen(buf)-1] == '\n' ) {
            buf[strlen(buf)-1] = 0;
        }       
        
        /* Check the buffer to see if the user simply hit ENTER without any input */
        if ( buf[0] == '\0' ) {
           
          /* Check for any child process that may have been terminated by the client,
           * and print a message with the pid of the terminated process. 
           * Loop will terminate when there are no more terminated children or no children at all. */
          while ( ( pid = waitpid( (pid_t) -1, NULL, WNOHANG ) ) > 0 ) { 
              
              fprintf(stderr, "[pid=%d] ", (int)pid);
              fprintf(stderr, "mdb-lookup-server terminated\n");
            
          
          }
              printf("port number: "); 

        } else {
           
           /* If the user entered a port number, fork a new process */
           pid = fork();

           if ( pid < 0 ) {
              
               die("fork failed");
           
           } else if ( pid > 0 ) {

               /* Parent Process should be evaluated first */               
               fprintf(stderr, "port number: "); 
               fprintf(stderr, "[pid=%d] ", (int)pid);
               fprintf(stderr, "mdb-lookup-server started on port %s\n", buf);
           
           } else {

               /* Run the shell script in the child process to start a new
                * instance of the 'mdb-lookup-server' */
               execl("./mdb-lookup-server-nc.sh", "mdb-lookup-server-nc.sh", buf, (char *)0);
               die("execl failed!");
           
           }

           printf("port number: ");
 
        }

    }

    return 0;
}
