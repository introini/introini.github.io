#include <stdio.h>
#include "prime.h"
#include "gcd.h"

int main()
{
    // Variable Declarations
    int x;
    int y;
    float avg;

    // Scan for user input
    scanf("%d", &x);
    scanf("%d", &y);

    // Calculate average
    //avg = ((float)x+(float)y)/(float)2;
    avg = (x+y)/(float)2;
    
    // Print results to the screen
    printf("You typed in %d and %d\n", x,y);
    printf("The average is: %.2f\n", avg);

    
    // This set of if statements will test if either number is prime
    // isPrime will return 0 if the number is prime.
    // Is x prime?
    if (isPrime(x) == 0)
    {
        printf("%d is Prime\n", x);
    } else {
        printf("%d is not Prime\n", x);
    }

    // Is y Prime?
    if (isPrime(y) == 0)
    {
        printf("%d is Prime\n", y);
    } else {
        printf("%d is not Prime\n", y);
    }


    // Are x and y Coprime numbers?
    // If the gcd function returns a number greater than 1
    // then we know the numbers are not coprime.
    if (x || y == 1) 
    {
        printf("%d and %d are Coprime!\n", x,y);
    } else {
       if (gcd(x,y) > 1)
         {
             printf("%d and %d are NOT Coprime\n", x,y);
         } else {
             printf("%d and %d are Coprime!\n", x,y);
         }
    }

   return 0;
}
