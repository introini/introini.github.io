#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include "mdb.h"
#include <ctype.h>
/*
 * freeNodes will allow us to free memory allocated for each
 * of the structures being passed into the linked list.
 *
 */
void freeNodes(void *p){
    free(p);
}

/*
 * This function takes the address of the list (passed in as a param)
 * and prints out each record as it walks through the list.
 * 'int num' will keep track of how many records we've gone through.
 *
 */
void printRecs(struct List *list) {
    struct Node *node = list->head;
    struct MdbRec *pRec;
    int num = 1;
    while (node) {
        pRec = node->data;
        fprintf(stderr, "%4d: {%s} said {%s}\n", num, pRec->name, pRec->msg);
        num++;
        node = node->next;
    }
    
    printf("\n");

}

/*
 * Our search method will take in the address of a list, and a pointer to
 * the search term we're interested in. Using these two parameters, we can
 * walk the list checking if the node contains any part of the search term
 * we've passed it.
 *
 * It's important to make sure that the last character of the search term be '\0',
 * otherwise the strstr() function will fail.
 *
 */
void searchMdb(struct List *list, char *q) {

    size_t len = strlen(q);
    struct Node *node = list->head;
    struct MdbRec *pRec;
    int num = 1;

    if (!(isprint(q[len-1]))) {
        q[len-1] = '\0';
    }
    
    while (node) {

        pRec = node->data;
        if (strstr(pRec->name,q) || strstr(pRec->msg,q)) { 
            fprintf(stderr, "%4d: {%s} said {%s}\n",num, pRec->name, pRec->msg);
        }
        num++;
        node = node->next;
    }

    printf("\n");
}

/*
 * Our main function will run the core part of this program.
 * This includes opening the databas for reading, adding each
 * record into a linked list, and requesting input from the user
 *
 */
int main(int argc, char **argv) {

    FILE *fp;
    char *program = argv[0]; /* Program Name */
    char *database = argv[1]; /* First Argument: Database name */

    struct List list;
    struct Node *node = NULL;
    struct MdbRec *ptrRec;
   
    const char *s = "r"; // Read Only flag
    
    char search[1000]; // Enough space in the char[] to read in a large input
    int  sTrunclen = 6; // Cutoff with space for 5 char and '\0'
    char sTrunc[sTrunclen];

    /* Error Checking for correct usage */
    if(argc != 2) {
        fprintf(stderr, "usage: %s <database_file>\n", program);
        exit(1);
    } 
    
    /* Opening the database file as read-only. We also need to make sure
     * that we can exit properly if for some reason the file cannot be 
     * opened.
     */
    if ((fp = fopen(database, s)) == NULL) {
        fprintf(stderr, "%s: No such file or directory\n", database);
        exit(1);
    }
    
    /* Here we need to initialize the list and build each
     * node with the correct amount of memory before we add a its address
     * to the linked list.
     * 
     */
    initList(&list);
    while (!feof(fp)){
        ptrRec = (struct MdbRec *) malloc(sizeof(struct MdbRec));    
        if (ptrRec == NULL) { perror("malloc() error"); exit(1); }
        memset(ptrRec, 0, sizeof(struct MdbRec));

        /* Reading the file 40 bytes at a time will allow us to
         * efficiently construct the linked list
         */
        if (fread(ptrRec,40,1,fp) == 1 ) {
            node = addAfter(&list,node,(void *)ptrRec); 
            if (node == NULL) {
                perror("error");
                exit(1);
            }
        }
    }

    /* This loop will actually serve as the main part of 
     * our program. Using fgets, we can take input from stdin, 
     * and then truncate it to fit our 5 char requirement.
     * strncpy() will do the actual truncating, and the rest will
     * be handled by 'searchMdb' or 'printRecs', depending
     * on what the user has input.
     */ 
    fprintf(stdout, "lookup: "); 
    while (fgets(search,sizeof(search),stdin) != NULL) {
        memset(sTrunc,'\0',sTrunclen);
        strncpy(sTrunc, search, 5);
        /* Here we can pass the user's search term to searchMdb unless
         * they simply pressed the 'return' without typing a search term.
         */
        if (isprint(sTrunc[0])) {
            searchMdb(&list,sTrunc); 
        } else {
            printRecs(&list);    
        }

        fprintf(stdout, "lookup: ");  
    }

    /* Check for any errors that occured while opening the file */
    if (ferror(fp)) {
        perror("error");
        exit(1);
    }         

    /* Free all memory allocated */
    free(ptrRec);
    traverseList(&list,&freeNodes);
    removeAllNodes(&list);
    
    /* Close the database */
    fclose(fp);

    return 0;
}
