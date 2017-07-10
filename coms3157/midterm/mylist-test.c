#include <stdlib.h>
#include "mylist.h"
#include <stdio.h>
#include <assert.h>

int count_if_solution(struct List *list, int (*f)(void *));
int count_if_myanswer(struct List *list, int (*f)(void *));
int is_positive_int(void *data) { return *(int *)data > 0; }

int main()
{
    int i;
    struct List list;
    initList(&list);

    int a[5] = {5,0,-2,1,7};
    for (i = 0; i < 5; i++) {
        addFront(&list, &a[i]);
    }
    fprintf(stderr, "SOLUTION:  %d\n", count_if_solution(&list, is_positive_int));
    fprintf(stderr, "MY ANSWER: %d\n", count_if_myanswer(&list, is_positive_int));
    removeAllNodes(&list);

    return 0;
}
