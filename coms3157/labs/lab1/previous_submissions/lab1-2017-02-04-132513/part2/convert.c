#include <stdio.h>
#include "bin.h"

int main()
{
    // Declaring vars
    int x;
    int i;
    int j;

    // Begin user input request
    scanf("%d", &x);
    
    // Begin output of each format
    printf("signed dec:\t%d\n", x);
    printf("unsigned dec:\t%u\n", x);
    printf("hex:\t\t%x\n", x);
    
    // Begin printing the Binary representation of x
    printf("binary:\t\t");
  
    // Outer loop: 8 times decending from 8 to 1
    //   Represents each byte and adds a space in between
    // Inner loop: 4 times decending from 32 to 1 
    //   Represents each bit position in the byte
    //   for example:  7654 3210
    for ( i=8; i>0; --i )
    {
        for ( j=i*4; j>(i*4)-4; --j)
        {
            printf("%d", getbits(x,(j)-1,1));
        }
        printf("%c", 32);
    }

    printf("\n");
    
    return 0;
}
