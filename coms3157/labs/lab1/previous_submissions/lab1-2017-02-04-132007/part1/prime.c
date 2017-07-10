#include "prime.h"

int isPrime(int x)
{
    int remainder;
    int divisor = 2;
    int result;

    // Get Remainder
    remainder = x % divisor;

    // Catch x = 1
    if (x == 1)
    {
        return 0;
    }

    // Catch numbers divisible by 2
    if (remainder == 0)
    {
        return divisor;
    }

    // Loop through 3 and above    
    while (remainder != 0) 
    {
        divisor++;
        remainder = x % divisor;
        
        if (remainder == 0 && divisor == x)
        {
            result = 0;
        } else if (remainder == 0) {
            result = divisor;
        }
    }

    return result;
}
