package org.codeexample.algorithms.collected.bit;

/**
 * The "parity" of a string refers to whether it contains an odd or an even
 * number of 1-bits. The string has "odd parity" if it contains an odd number of
 * 1-bits; otherwise, it has "even parity."
 * 
 * 
 * 
 */
public class Parity
{
    int parity1(
            int x)
    {
        int y;
        y = x ^ (x >> 1);
        y = y ^ (y >> 2);
        y = y ^ (y >> 4);
        y = y ^ (y >> 8);
        y = y ^ (y >> 16);
        return y & 1;
    }

}
