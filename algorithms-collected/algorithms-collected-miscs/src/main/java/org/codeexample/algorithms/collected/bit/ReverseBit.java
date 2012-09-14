package org.codeexample.algorithms.collected.bit;

//By "reversing bytes" we mean a similar reflection of the four bytes of a register. 
//Byte reversal is a necessary operation to convert data between the "little-endian" format used by DEC and Intel and 
//the "big-endian" format used by most other manufacturers.
//
//Bit reversal can be done quite efficiently by interchanging adjacent single bits, then interchanging adjacent 2-bit fields, and so on,
//
//x = (x & 0x55555555) <<  1 | (x & 0xAAAAAAAA) >>  1; 
//x = (x & 0x33333333) <<  2 | (x & 0xCCCCCCCC) >>  2; 
//x = (x & 0x0F0F0F0F) <<  4 | (x & 0xF0F0F0F0) >>  4; 
//x = (x & 0x00FF00FF) <<  8 | (x & 0xFF00FF00) >>  8; 
//
//A small improvement results on most machines by using fewer distinct large constants and 
//doing the last two assignments in a more straightforward way, as is shown in Figure 7-1 (30 basic RISC instructions, branch-free).
//
//Figure 7-1 Reversing bits.
//unsigned rev(unsigned x) {
//   x = (x & 0x55555555) <<  1 | (x >>  1) & 0x55555555; 
//   x = (x & 0x33333333) <<  2 | (x >>  2) & 0x33333333; 
//   x = (x & 0x0F0F0F0F) <<  4 | (x >>  4) & 0x0F0F0F0F; 
//   x = (x << 24) | ((x & 0xFF00) << 8) | 
//       ((x >> 8) & 0xFF00) | (x >> 24); 
//   return x; 
//} 
//
//Generalized Bit Reversal
//[GLS1] suggests that the following sort of generalization of bit reversal, which he calls "flip," 
//is a good candidate to consider for a computer's instruction set:
//
//if (k &  1) x = (x & 0x55555555) <<  1 | (x & 0xAAAAAAAA) >>  1; 
//if (k &  2) x = (x & 0x33333333) <<  2 | (x & 0xCCCCCCCC) >>  2; 
//if (k &  4) x = (x & 0x0F0F0F0F) <<  4 | (x & 0xF0F0F0F0) >>  4; 
//if (k &  8) x = (x & 0x00FF00FF) <<  8 | (x & 0xFF00FF00) >>  8; 
//if (k & 16) x = (x & 0x0000FFFF) << 16 | (x & 0xFFFF0000) >> 16; 
//(The last two and operations can be omitted.) For k = 31, this operation reverses the bits in a word. 
//For k = 24, it reverses the bytes in a word. For k = 7, it reverses the bits in each byte, without changing the positions of the bytes. 
//For k = 16, it swaps the left and right halfwords of a word, and so on. In general, it moves the bit at position m to position m k.
//It can be implemented in hardware very similarly to the way a rotate shifter is usually implemented (five stages of MUX's,
//with each stage controlled by a bit of the shift amount k).
//        
public class ReverseBit
{

    public static long reverseBit1(
            long x)
    {
        long b = 0;
        while (x != 0)
        {
            b <<= 1;
            b |= (x & 1);
            x >>= 1;
        }
        return b;
    }

    /**
     * Returns the value obtained by reversing the order of the bits in the
     * two's complement binary representation of the specified {@code int}
     * value.
     * 
     * @return the value obtained by reversing order of the bits in the
     *         specified {@code int} value.
     * @since 1.5
     */
    public static int reverse(
            int i)
    {
        // HD, Figure 7-1

        // x = (x & 0x55555555) << 1 | (x & 0xAAAAAAAA) >> 1;
        // x = (x & 0x33333333) << 2 | (x & 0xCCCCCCCC) >> 2;
        // x = (x & 0x0F0F0F0F) << 4 | (x & 0xF0F0F0F0) >> 4;
        // x = (x & 0x00FF00FF) << 8 | (x & 0xFF00FF00) >> 8;
        // x = (x & 0x0000FFFF) << 16 | (x & 0xFFFF0000) >> 16;

        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        // don't understand this, why it equals:
        // x = (x & 0x00FF00FF) << 8 | (x & 0xFF00FF00) >> 8;
        // x = (x & 0x0000FFFF) << 16 | (x & 0xFFFF0000) >> 16;
        i = (i << 24) | ((i & 0xff00) << 8) | ((i >>> 8) & 0xff00) | (i >>> 24);
        return i;
    }

    // Generalized Bit Reversal
    // [GLS1] suggests that the following sort of generalization of bit
    // reversal, which he calls "flip," is a good candidate to consider for a
    // computer's instruction set:
    //
    // if (k & 1) x = (x & 0x55555555) << 1 | (x & 0xAAAAAAAA) >> 1;
    // if (k & 2) x = (x & 0x33333333) << 2 | (x & 0xCCCCCCCC) >> 2;
    // if (k & 4) x = (x & 0x0F0F0F0F) << 4 | (x & 0xF0F0F0F0) >> 4;
    // if (k & 8) x = (x & 0x00FF00FF) << 8 | (x & 0xFF00FF00) >> 8;
    // if (k & 16) x = (x & 0x0000FFFF) << 16 | (x & 0xFFFF0000) >> 16;
    // (The last two and operations can be omitted.) For k = 31, this operation
    // reverses the bits in a word. For k = 24, it reverses the bytes in a word.
    // For k = 7, it reverses the bits in each byte, without changing the
    // positions of the bytes. For k = 16, it swaps the left and right halfwords
    // of a word, and so on. In general, it moves the bit at position m to
    // position m k. It can be implemented in hardware very similarly to the way
    // a rotate shifter is usually implemented (five stages of MUX's, with each
    // stage controlled by a bit of the shift amount k).

    /**
     * Returns the value obtained by reversing the order of the bits in the
     * two's complement binary representation of the specified {@code long}
     * value.
     * 
     * @return the value obtained by reversing order of the bits in the
     *         specified {@code long} value.
     * @since 1.5
     */
    public static long reverse(
            long i)
    {
        // HD, Figure 7-1
        i = (i & 0x5555555555555555L) << 1 | (i >>> 1) & 0x5555555555555555L;
        i = (i & 0x3333333333333333L) << 2 | (i >>> 2) & 0x3333333333333333L;
        i = (i & 0x0f0f0f0f0f0f0f0fL) << 4 | (i >>> 4) & 0x0f0f0f0f0f0f0f0fL;
        i = (i & 0x00ff00ff00ff00ffL) << 8 | (i >>> 8) & 0x00ff00ff00ff00ffL;
        i = (i << 48) | ((i & 0xffff0000L) << 16) | ((i >>> 16) & 0xffff0000L) | (i >>> 48);
        return i;
    }

}
