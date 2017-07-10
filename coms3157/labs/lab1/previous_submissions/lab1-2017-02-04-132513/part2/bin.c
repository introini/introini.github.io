#include "bin.h"
#include <stdio.h>

// This function comes from the K&R book (page 49)
// Will return the range of bits in x (p to n) where
// p is a position and n is the number of bits
// to evalaluate. 
unsigned getbits(unsigned x, int p, int n)
{
    return ( x >> (p+1-n) ) & ~( ~0 << n );
}

