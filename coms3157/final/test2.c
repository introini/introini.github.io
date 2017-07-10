#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>


int main()
{
	uint64_t MAGIC = ~((uint64_t)0);

	printf("%lu -> ", sizeof(MAGIC));
	printf("%lu\n", MAGIC<<8);


	int a[10] = {1,2,3,4,5,6,7,8,9,10};

	int *p = a+3;
	int *q = &a[9];

	printf("ADDRESS OF p: %p\n",p);
	printf("ADDRESS OF q: %p\n",q);
	q--;
	printf("ADDRESS OF q: %p\n",q);
	int x = q-p;
	int y = q+p;

	printf("ADDRESS OF p: %p\n",p);
	printf("ADDRESS OF q: %p\n",q);
	printf("q - p: %lu\n",q-p);
	printf("%d\n",x);
	printf("%d\n",y);


	return 0;
}
