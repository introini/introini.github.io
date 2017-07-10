#include "mylist.h"
#include <stdlib.h>
#include <stdio.h>

static struct Node *node;

/* Helper function to allocate memory large enough for 
 * our Nodes.
 */
struct Node *nalloc(void) {
    return (struct Node *) malloc(sizeof(struct Node));
};

/* Add the given data to the front of the list */
struct Node *addFront(struct List *list, void *data) {

    node = nalloc();
   
    if (node == NULL){
        perror("malloc failed");
        exit(1);
    }
   
    node->data = data;
    node->next = list->head;
    list->head = node;

    return node;
}

void traverseList(struct List *list, void (*f)(void *)) {
    /*
     * We'll need to create a pointer of type struct Node
     * in order to keep track of where we are in the list.
     * Then, we can assign the head to that pointer so we
     * can determine when it's time to exit the loop. In our
     * case, we want to print the list from first to last.
     *
     */

    for(node = list->head; node != NULL; node = node->next) {
        f(node->data); /* Passing the data to the function pointer */
    }
}

void flipSignDouble(void *data) {
    /* 
     * We can create a pointer of type double that
     * takes in the void pointer 'data' as a value. Then, 
     * we can dereference pointer of type double and
     * set it's value to the value of the right hand
     * expresion.
     *
     * '*((double *)data) * -1' casts 'data' to a
     * pointer of type double, which then gets derreferenced
     * and multiplied by -1 to switch signs.
     */
    double *pd = data;
    *pd =  *((double *)data) * -1; //sets val to value of -data

}

int compareDouble(const void *data1, const void *data2) {

    const double *p1 = data1;
    const double *p2 = data2;

    if (*p1 == *p2) {
        return 0;
    }
    return 1;
}



struct Node *findNode(struct List *list, const void *dataSought,
        int (*compar)(const void *, const void *)) {

    /* Traverse the list until we find the node that
     * we're looking for. If compar returns 0, then we
     * break out and return a pointer to the node we found.
     * Otherwise we continue advancing through the list until
     * we reach the end of the list.
     */ 
    for(node = list->head; node != NULL; node = node->next) {
       /* Passing a pointer to the current node and dataSought
        * to the function pointer 'compar' will return 0 if
        * the two values match, or 1 if they don't
        */
        if(compar(node->data, dataSought) == 0) {
            return node;
        } 
    }

    return NULL;
}

void *popFront(struct List *list) {

    /* We'll need to store the address of list->head->data in order
     * to return its value and free the memory location afterwards.
     */
    void *popped;
    void *nodeAddress;
    node = list->head;
    nodeAddress = &node->data;

    /* Here we'll check if the list is empty before we set the value
     * of popped to node->data (current list->head->data), and point
     * list->head to the next node in the list.
     * Then, we can free the memory allocated at the address we
     * stored before the check. Lastly, if the list is empty, we
     * can just return a NULL pointer.
     */
    if (!isEmptyList(list)) {
        popped = node->data;
        list->head = node->next;    
        free(nodeAddress);
    } else {
        return NULL;
    }
    
    return popped;
}
/* Continually check if the list is empty, if it's not empty call
 * popFront() until there are no more elements to pop.
 */
void removeAllNodes(struct List *list) {

    while(!isEmptyList(list)) {
        popFront(list);
    }
    
}

/* Here we need to keep track of the last node on the list with 
 * 'prevNode. We can call 'addFront()' if the the 'prevNode' 
 * argument is NULL, otherwise we need to walk through the list 
 * from the begining until we reach a pointer to NULL. 
 * We then make sure we're at the last node by checking if our 
 * pointer (head) is = to 'prevNode'. 
 *
 * Next allocate enough memory for the new node, set the data
 * value, and next pointers appropriately.
 */
struct Node *addAfter(struct List *list, 
        struct Node *prevNode, void *data) {

    struct Node *ptr;
    node = prevNode;
   
    /* Making sure that we're covered if the list is empty */
    if (prevNode == NULL) {

       node = addFront(list,data);
    
    } else {

        /* Walk throught the list */
        for(ptr = list->head; ptr != NULL ; ptr = ptr->next) {
            /* Check we're at the last element */
            if(ptr == prevNode) {
                /* Make the new node */
                node = nalloc();
                
                if (node == NULL){
                    perror("malloc failed");
                    return NULL;
                }
                
                /* Re-assigning pointers and setting data */
                node->data = data;
                prevNode->next = node;
                node->next = NULL;
            }
        } 
    }

  return node;

}
/* We can reverse the list by keeping track of
 * 3 pointers at any given time, and simply rearange
 * what they point to until the current element == NULL.
 */
void reverseList(struct List *list){

    struct Node *prv = NULL;
    struct Node *cur = list->head;
    struct Node *nxt;

    while (cur) {

    nxt = cur->next;
    cur->next = prv;
    prv = cur;
    cur = nxt;

    }

    list->head = prv;
}
