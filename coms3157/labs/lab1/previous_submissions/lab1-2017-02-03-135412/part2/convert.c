#include <stdio.h>
#include "bin.h"

int main()
{
    int x;
    int i;
    scanf("%d", &x);
    
    printf("signed dec:\t%d\n", x);
    printf("unsigned dec:\t%u\n", x);
    printf("hex:\t\t%x\n", x);
    printf("binary:\t\t%d\n", x);
 
    for ( i=32; i>0; --i )
    {
       printf("%d", getbits(x,i-1,1));
    } 
    
    return 0;
}
