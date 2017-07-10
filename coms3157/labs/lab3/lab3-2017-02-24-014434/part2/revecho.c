#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include "mylist.h"

/* This function will be passed to the
 * traverseList function in order to print
 * each argument to the screen. 
 */
static void printArgs(void *p) {
    fprintf(stderr,"%s\n", (char *)p);
}

/* 'die' will simply print out the message
 * being passed as argument, and exit the program
 * when it's called.
 */
static void die(const char *message) {
    perror(message);
    exit(1);
}

int main(int argc, char **argv){

    struct Node *node; // Used to store the value of findNode()
    struct List list;
    char str[] = "dude";  
   

    if (!(argc > 1))
        die("You forgot to include arguments. Exiting!");


    /* Initialising the list by passing the address to list */
    initList(&list);

    /* We need to iterate through each of the arguments
     * passed and add them to the front of our list.
     * Then, we can traverse the list in order to display
     * the items in reverse order. 
     */
    argv++;
    while(*argv) {

        if(addFront(&list, (void *)*argv++) == NULL) {
             die("addFront() failed");
        }
    }

    traverseList(&list, &printArgs);

    printf("\n");
    


    /* In order to find the node we care about, we will call
     * the findNode() function from libmylist, and pass it
     * refferences to the 'list', 'str', and 'strcmp'.
     *
     * Note: strcmp has been casted to a function pointer type
     * that corresponds to the one we need for libmylist.
     *
     * If findNode is successfull, it will return a 'struct Node*' 
     * which we'll assign to 'node'. We can then print a message
     * saying that we found the string we're interested in. 
     * Otherwise we'll print that we didn't find the string we 
     * were looking for.
     */
    if((node = findNode(&list, &str, (int (*)(const void *,const void *))strcmp)) && node != NULL) {

        fprintf(stdout, "%s found\n", (char *)node->data);
    
    } else {

        fprintf(stderr, "%s not found\n", str);
    
    }
    
    /* Free any memory allocated for the list */
    removeAllNodes(&list);

    return 0;
}
