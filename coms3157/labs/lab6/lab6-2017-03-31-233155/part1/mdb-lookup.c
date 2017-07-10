#include "includes.h" 

#define SNDBUFSIZE 56 /* Enough space for the entire line */

void DieWithError(char *errorMessage);
/*
 * freeNodes will allow us to free memory allocated for each of the structures
 * being passed into the linked list.
 *
 */
void freeNodes(void *p){ free(p); }

void HandleTCPClient(char *database, int clntSocket) {

    FILE *fp;
    
    struct List list; struct Node *node = NULL; struct MdbRec *ptrRec;
 
    const char *s = "r"; // Read Only flag 
    char search[1000]; // Enough space in the char[] to read in a large input
    int  sTrunclen = 6; // Cutoff with space for 5 char and '\0' char
    char sTrunc[sTrunclen], sndBuffer[SNDBUFSIZE];
    char blankLine[2] = {'\n','\0'};
    int  sndMsgSize;

    /* Opening the database file as read-only. We also need to make sure that
     * we can exit properly if for some reason the file cannot be opened.
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
    while (!feof(fp) && !ferror(fp)){
       
        ptrRec = (struct MdbRec *) malloc(sizeof(struct MdbRec));    
        if (ptrRec == NULL) { perror("malloc() error"); exit(1); }
        memset(ptrRec, 0, sizeof(struct MdbRec));

        /* Reading the file 40 bytes at a time will allow us to
         * efficiently construct the linked list
         */
        if (fread(ptrRec,40,1,fp) == 1) {
            node = addAfter(&list,node,(void *)ptrRec); 
            if (node == NULL) {
                perror("error");
                exit(1);
            }
        }
    }

    
    /* wrapping socket descriptor in a FILE * */
    FILE *input = fdopen(clntSocket, s);
    while (fgets(search,sizeof(search),input) != NULL) {
        /* Clean up sTrunc */        
        memset(sTrunc,'\0',sTrunclen);
        strncpy(sTrunc, search, 5);
        
        size_t len = strlen(sTrunc); 
        if ( !( isprint( sTrunc[len-1] ) ) ) { 
                sTrunc[len-1] = '\0'; 
        }        
        
        /* Search the list */
        int num = 1;
        struct Node *node = list.head; 
        while (node) {
        
            struct MdbRec *pRec = (struct MdbRec *)node->data; 
            if (strstr(pRec->name,sTrunc) || strstr(pRec->msg,sTrunc)) {
            /* Usign snprintf() allows us to pass the data we found to 
             * a string so that we can send it across the wire.
             * 
             * sndMsgSize ensures that we transmit the correct amount of bytes
             */   
                sndMsgSize = snprintf(sndBuffer, SNDBUFSIZE, "%4d: {%s} said {%s}\n",num, pRec->name,pRec->msg); 
                if (send(clntSocket, sndBuffer, sndMsgSize,0) != sndMsgSize)
                    DieWithError("send() failed");
                } 
            
            node = node->next; 
            num++; 
        }      
        
        /* Send a blank line */
        len = strlen(blankLine);
        if (send(clntSocket, blankLine, len,0) != len)
            DieWithError("send() failed");
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
    fclose(input);

}
