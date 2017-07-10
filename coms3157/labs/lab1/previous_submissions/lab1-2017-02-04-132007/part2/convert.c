#include <stdio.h>
#include "bin.h"

int main()
{
    int x;
    int i;
    int j;

    scanf("%d", &x);
    
    printf("signed dec:\t%d\n", x);
    printf("unsigned dec:\t%u\n", x);
    printf("hex:\t\t%x\n", x);
    printf("binary:\t\t");
    
    // i = byte
    for ( i=8; i>0; --i )
    {
        for ( j=i*4; j>(i*4)-4; --j)
        {
            printf("%d", getbits(x,(j)-1,1));
        }
        printf(" ");
    }

    printf("\n");
    return 0;
}
