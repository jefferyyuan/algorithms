package org.codeexample.algorithms.collected.bit;

public class LeadingTrailingZeros
{

    /**
     * Returns the number of zero bits preceding the highest-order ("leftmost")
     * one-bit in the two's complement binary representation of the specified
     * {@code int} value. Returns 32 if the specified value has no one-bits in
     * its two's complement representation, in other words if it is equal to
     * zero.
     * 
     * <p>
     * Note that this method is closely related to the logarithm base 2. For all
     * positive {@code int} values x:
     * <ul>
     * <li>floor(log<sub>2</sub>(x)) = {@code 31 - numberOfLeadingZeros(x)}
     * <li>ceil(log<sub>2</sub>(x)) = {@code 32 - numberOfLeadingZeros(x - 1)}
     * </ul>
     * 
     * @return the number of zero bits preceding the highest-order ("leftmost")
     *         one-bit in the two's complement binary representation of the
     *         specified {@code int} value, or 32 if the value is equal to zero.
     * @since 1.5
     */
    public static int numberOfLeadingZeros(
            int x)
    {
        // HD, Figure 5-6
        if (x == 0)
            return 32;
        int n = 1;
        if (x >>> 16 == 0)
        {
            n += 16;
            x <<= 16;
        }
        if (x >>> 24 == 0)
        {
            n += 8;
            x <<= 8;
        }
        if (x >>> 28 == 0)
        {
            n += 4;
            x <<= 4;
        }
        if (x >>> 30 == 0)
        {
            n += 2;
            x <<= 2;
        }
        n -= x >>> 31;
        return n;
    }

    /**
     * Returns the number of zero bits following the lowest-order ("rightmost")
     * one-bit in the two's complement binary representation of the specified
     * {@code int} value. Returns 32 if the specified value has no one-bits in
     * its two's complement representation, in other words if it is equal to
     * zero.
     * 
     * @return the number of zero bits following the lowest-order ("rightmost")
     *         one-bit in the two's complement binary representation of the
     *         specified {@code int} value, or 32 if the value is equal to zero.
     * @since 1.5
     */
    public static int numberOfTrailingZeros(
            int x)
    {
        // HD, Figure 5-14
        int y;
        if (x == 0)
            return 32;
        int n = 31;
        y = x << 16;
        if (y != 0)
        {
            n = n - 16;
            x = y;
        }
        y = x << 8;
        if (y != 0)
        {
            n = n - 8;
            x = y;
        }
        y = x << 4;
        if (y != 0)
        {
            n = n - 4;
            x = y;
        }
        y = x << 2;
        if (y != 0)
        {
            n = n - 2;
            x = y;
        }
        return n - ((x << 1) >>> 31);
    }

    public static void main(
            String[] args)
    {
        System.out.println(numberOfLeadingZeros(0x2AFFFFFF));
    }
}
