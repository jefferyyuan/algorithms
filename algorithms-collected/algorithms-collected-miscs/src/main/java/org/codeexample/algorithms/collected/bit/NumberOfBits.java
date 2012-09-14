package org.codeexample.algorithms.collected.bit;

/**
 * This is also called population count.
 * Source:
 * http://stackoverflow.com/questions/109023/best-algorithm-to-count-the-number-
 * of-set-bits-in-a-32-bit-integer
 * 
 * <p>
 * Source: http://www.hackersdelight.org/HDcode/pop.c.txt
 * <p>
 * www.hackersdelight.org/HDcode.zip
 */
public class NumberOfBits
{

    /**
     * Returns the number of one-bits in the two's complement binary
     * representation of the specified {@code long} value. This function is
     * sometimes referred to as the <i>population count</i>.
     * 
     * @return the number of one-bits in the two's complement binary
     *         representation of the specified {@code long} value.
     * @since 1.5
     */
    public static int bitCount(
            long i)
    {
        // HD, Figure 5-14
        i = i - ((i >>> 1) & 0x5555555555555555L);
        i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
        i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        i = i + (i >>> 32);
        return (int) i & 0x7f;
    }

    /**
     * Returns the number of one-bits in the two's complement binary
     * representation of the specified {@code int} value. This function is
     * sometimes referred to as the <i>population count</i>.
     * <p>Source: From Hacker's Delight, p. 66, Figure 5-2
     * @return the number of one-bits in the two's complement binary
     *         representation of the specified {@code int} value.
     * @since 1.5
     */
    public static int bitCount(
            int x)
    {
        // HD, Figure 5-2
        x = x - ((x >>> 1) & 0x55555555);
        x = (x & 0x33333333) + ((x >>> 2) & 0x33333333);
        x = (x + (x >>> 4)) & 0x0f0f0f0f;
        x = x + (x >>> 8);
        x = x + (x >>> 16);
        return x & 0x3f;
    }

    /**
     * Source: http://www.hackersdelight.org/HDcode/pop.c.txt
     */
    public static int pop0(
            int x)
    {
        x = (x & 0x55555555) + ((x >> 1) & 0x55555555);
        x = (x & 0x33333333) + ((x >> 2) & 0x33333333);
        x = (x & 0x0F0F0F0F) + ((x >> 4) & 0x0F0F0F0F);
        x = (x & 0x00FF00FF) + ((x >> 8) & 0x00FF00FF);
        x = (x & 0x0000FFFF) + ((x >> 16) & 0x0000FFFF);
        return x;
    }

    /**
     * Counting 1-bits in a sparsely populated word.
     * <p>
     * This has a dual algorithm that is applicable if the number of 1-bits is
     * expected to be large. The dual algorithm keeps turning on the rightmost
     * 0-bit with x = x | (x + 1), until the result is all 1's (-1). Then, it
     * returns 32 - n. (Alternatively, the original number x can be
     * complemented, or n can be initialized to 32 and counted down).
     */
    public static int pop4(int x) {
        int n=0;
        while (x != 0) {
           n = n + 1;
           // clear the least significant bit set
           x = x & (x - 1);
        }
        return n;
     }

    public static  int pop5a(int x) {
        int sum =x;
         // Shift right & subtract
            while (x != 0) {
               x = x >> 1;
               sum = sum - x;
            }
            return sum;
     }

    // Lookup table for fast calculation of bits set in 8-bit unsigned char.
    // A less interesting algorithm that may be competitive with all the
    // algorithms for pop(x) in this section is to have a table that contains
    // pop(x) for, say, x in the range 0 to 255. The table can be accessed four
    // times, adding the four numbers obtained.
    private static int oneBitsInByte[] = {
            // 0 1 2 3 4 5 6 7 8 9 A B C D E F (<- n)
            0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4,
            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,

            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,

            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,

            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
            4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8
          };

    // Function for fast calculation of bits set in 16-bit unsigned short.

    int oneBitsInShort(
            short x)
    {
        return oneBitsInByte[x >> 8] + oneBitsInByte[x & 0xff];
    }

    // Function for fast calculation of bits set in 32-bit unsigned int.

    int oneBitsInUInt(
            int x)
    {
        return oneBitsInShort((short) (x >> 16)) + oneBitsInShort((short) (x & 0xffff));
    }
    

public static int pop6(int x) {               // Table lookup.
   return oneBitsInByte[x         & 0xFF] +
           oneBitsInByte[(x >>  8) & 0xFF] +
          oneBitsInByte[(x >> 16) & 0xFF] +
          oneBitsInByte[(x >> 24)];
}

    int errors;
    void error(int x, int y) {
       errors = errors + 1;
       System.out.printf("Error for x = %08x, got %08x\n", x, y);
    }

    public static void main(
            String[] args)
    {
         int test[] = {0,0, 1,1, 2,1, 3,2, 4,1, 5,2, 6,2, 7,3,
           8,1, 9,2, 10,2, 11,3, 12,2, 13,3, 14,3, 15,4, 16,1, 17,2,
           0x3F,6, 0x40,1, 0x41,2, 0x7f,7, 0x80,1, 0x81,2, 0xfe,7, 0xff,8,
           0x4000,1, 0x4001,2, 0x7000,3, 0x7fff,15,
           0x55555555,16, 0xAAAAAAAA, 16, 0xFF000000,8, 0xC0C0C0C0,8,
           0x0FFFFFF0,24, 0x80000000,1, 0xFFFFFFFF,32};
         int i, n = test.length;
         
       int setBits= pop0(-1);
       System.out.println(setBits);
    }

}
