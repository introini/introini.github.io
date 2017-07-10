#include "prime.h"

int isPrime(int x)
{
    int remainder;
    int divisor = 2;
    int result;
    // Get Remainder
    remainder = x % divisor;

    // Catch numbers divisible by 2
    if (remainder == 0)
    {
        result = divisor;
    }
    
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
