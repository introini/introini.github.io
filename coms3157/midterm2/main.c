#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include <ctype.h>
#include <stdio.h>
/*
 * freeNodes will allow us to free memory allocated for each
 * of the structures being passed into the linked list.
 *
 */
struct MdbRec { char name[16]; char msg[24]; };
int main(){

    FILE *in;
    FILE *out;
    struct MdbRec r;
    int mdb_rec_num;

    fprintf(stderr, "%lu\n", (10-1) * sizeof(r));


}
