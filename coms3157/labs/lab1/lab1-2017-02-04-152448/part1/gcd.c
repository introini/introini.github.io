#include "gcd.h"

int gcd(int a, int b)
{
    // Return the remainder of b%a until a = 0
    if (a == 0)
        return b;
    return gcd(b%a, a);
}

