#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
/*
 * Comparison functions for ascending and descinding sort
 */
int cmpIntDescending(const void *p1, const void *p2)
{
    /* Here we need to dereference p1 and p1 as well
     * as cast them to int * since that's what the value
     * of p1 and p2 is.
     */
    int x = *(int *)p1;
    int y = *(int *)p2;
    if (x < y){
       return 1;
    } else if (x > y){
       return -1;
    } else {
       return 0;
    }
}
int cmpIntAscending(const void *p1, const void *p2)
{
     /* Here we need to dereference p1 and p1 as well
     * as cast them to int * since that's what the value
     * of p1 and p2 is.
     */
    int x = *(int *)p1;
    int y = *(int *)p2;
    if (x < y){
       return -1;
    } else if (x > y){
       return 1;
    } else {
       return 0;
    }
}
void sort_integer_array(int *begin, int *end, int ascending)
{
   // Here i need to check wether we're sorting in ascending order
   // or descending order. Then run qsort appropriately.
   if (ascending == 1) 
       qsort(begin, (int)(end-begin+1), sizeof(int), &cmpIntAscending);
   else if (ascending == 0)
       qsort(begin, (int)(end-begin+1), sizeof(int), &cmpIntDescending);
}

int main() {

    /*
     * Take in the size of the array we will sort as
     * input from the user.
     */
    int SIZE;
    scanf("%d", &SIZE);
    srandom(time(NULL));

    /* Here we'll allocate enough memory to store
     * SIZE amount of ints.
     */
    int *p = (int *) malloc(SIZE * sizeof(int));

    /*
     * Check that we're not alocating 0 space
     */
    if (p == NULL) {
        perror("malloc failed");
        exit(1);
    }

    // Set each byte equal to 0
    memset(p, 0, SIZE * sizeof(int));

    // Here we'll call random() to get a random
    // number between 0-99 and assing that number
    // to each element.
    for (int i = 0; i<SIZE; i++)
        p[i] = random()%100;

    /* ASCENDING ORDER ARRAY
     * Now we can work on the copies of the original array
     * that was just created.
     */
    // Allocate space for the first copy of the Original Array
    int *p1 = (int *) malloc(SIZE * sizeof(int));
    // Set each byte equal to 0
    memset(p1, 0, SIZE * sizeof(int));
    
    /* This will copy the contents of the memory location
     * from p to p1 up to SIZE*sizeof(int) bytes.
     */
    memcpy(p1,p, SIZE * sizeof(int));
    
    // Sort the array in Ascending order by passing
    // in a reference to the first element of p1 and
    // a the address of the last element in p1. Then pass
    // 1 to indicate we want ascending.
    sort_integer_array(p1, &p1[SIZE-1],1);
 
 
    // DESCENDING ORDER ARRAY  
    // Allocate space for the second copy of the Original Array
    int *p2 = (int *) malloc(SIZE * sizeof(int));
   
    // Set each byte equal to 0
    memset(p2, 0, SIZE * sizeof(int));
    
     /* This will copy the contents of the memory location
     * from p to p2 up to SIZE*sizeof(int) bytes.
     */
    memcpy(p2,p, SIZE * sizeof(int));
    
     // Sort the array in Descending order by passing
    // in a reference to the first element of p2 and
    // a the address of the last element in p2.Then pass 0 to
    // indicate we want descending.
    sort_integer_array(p2, &p2[SIZE-1],0);

    // OUTPUT
    // Now we need to print the arrays
   
    // ORIGINAL - Random numbers
    printf("original: ");

    for (int i = 0; i<SIZE; i++)
        printf("%d ", *(p+i)); 

    printf("\n");

    // ASCENDING
    printf("ascending: ");

    for (int i = 0; i<SIZE; i++)
        printf("%d ", *(p1+i)); 

    printf("\n");

    // DESCENDING
    printf("descending: ");

    for (int i = 0; i<SIZE; i++)
        printf("%d ", *(p2+i)); 

    printf("\n");

    /*
     * Here we'll free the memeory we allocated to the heap.
     */

    free(p); // Original Array
    free(p1); // Copy 1
    free(p2); // Copy 2

    return 0;
}

