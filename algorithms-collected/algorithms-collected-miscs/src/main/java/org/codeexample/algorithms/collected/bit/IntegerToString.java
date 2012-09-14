package org.codeexample.algorithms.collected.bit;

public class IntegerToString
{
    /**
     * All possible chars for representing a number as a String
     */
    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * Returns a string representation of the first argument in the radix
     * specified by the second argument.
     * 
     * <p>
     * If the radix is smaller than {@code Character.MIN_RADIX} or larger than
     * {@code Character.MAX_RADIX}, then the radix {@code 10} is used instead.
     * 
     * <p>
     * If the first argument is negative, the first element of the result is the
     * ASCII minus character {@code '-'} (<code>'&#92;u002D'</code>). If the
     * first argument is not negative, no sign character appears in the result.
     * 
     * <p>
     * The remaining characters of the result represent the magnitude of the
     * first argument. If the magnitude is zero, it is represented by a single
     * zero character {@code '0'} (<code>'&#92;u0030'</code>); otherwise, the
     * first character of the representation of the magnitude will not be the
     * zero character. The following ASCII characters are used as digits:
     * 
     * <blockquote> {@code 0123456789abcdefghijklmnopqrstuvwxyz} </blockquote>
     * 
     * These are <code>'&#92;u0030'</code> through <code>'&#92;u0039'</code> and
     * <code>'&#92;u0061'</code> through <code>'&#92;u007A'</code>. If
     * {@code radix} is <var>N</var>, then the first <var>N</var> of these
     * characters are used as radix-<var>N</var> digits in the order shown.
     * Thus, the digits for hexadecimal (radix 16) are {@code 0123456789abcdef}.
     * If uppercase letters are desired, the
     * {@link java.lang.String#toUpperCase()} method may be called on the
     * result:
     * 
     * <blockquote> {@code Integer.toString(n, 16).toUpperCase()} </blockquote>
     * 
     * @param i
     *            an integer to be converted to a string.
     * @param radix
     *            the radix to use in the string representation.
     * @return a string representation of the argument in the specified radix.
     * @see java.lang.Character#MAX_RADIX
     * @see java.lang.Character#MIN_RADIX
     */
    public static String toString(
            int i, int radix)
    {

        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
            radix = 10;

        /* Use the faster version */
        if (radix == 10)
        {
            return toString(i);
        }

        char buf[] = new char[33];
        boolean negative = (i < 0);
        int charPos = 32;

        if (!negative)
        {
            i = -i;
        }

        while (i <= -radix)
        {
            buf[charPos--] = digits[-(i % radix)];
            i = i / radix;
        }
        buf[charPos] = digits[-i];

        if (negative)
        {
            buf[--charPos] = '-';
        }

        return new String(buf, charPos, (33 - charPos));
    }

    /**
     * Returns a string representation of the integer argument as an unsigned
     * integer in base&nbsp;16.
     * 
     * <p>
     * The unsigned integer value is the argument plus 2<sup>32</sup> if the
     * argument is negative; otherwise, it is equal to the argument. This value
     * is converted to a string of ASCII digits in hexadecimal (base&nbsp;16)
     * with no extra leading {@code 0}s. If the unsigned magnitude is zero, it
     * is represented by a single zero character {@code '0'} (
     * <code>'&#92;u0030'</code>); otherwise, the first character of the
     * representation of the unsigned magnitude will not be the zero character.
     * The following characters are used as hexadecimal digits:
     * 
     * <blockquote> {@code 0123456789abcdef} </blockquote>
     * 
     * These are the characters <code>'&#92;u0030'</code> through
     * <code>'&#92;u0039'</code> and <code>'&#92;u0061'</code> through
     * <code>'&#92;u0066'</code>. If uppercase letters are desired, the
     * {@link java.lang.String#toUpperCase()} method may be called on the
     * result:
     * 
     * <blockquote> {@code Integer.toHexString(n).toUpperCase()} </blockquote>
     * 
     * @param i
     *            an integer to be converted to a string.
     * @return the string representation of the unsigned integer value
     *         represented by the argument in hexadecimal (base&nbsp;16).
     * @since JDK1.0.2
     */
    public static String toHexString(
            int i)
    {
        return toUnsignedString(i, 4);
    }

    /**
     * Returns a string representation of the integer argument as an unsigned
     * integer in base&nbsp;8.
     * 
     * <p>
     * The unsigned integer value is the argument plus 2<sup>32</sup> if the
     * argument is negative; otherwise, it is equal to the argument. This value
     * is converted to a string of ASCII digits in octal (base&nbsp;8) with no
     * extra leading {@code 0}s.
     * 
     * <p>
     * If the unsigned magnitude is zero, it is represented by a single zero
     * character {@code '0'} (<code>'&#92;u0030'</code>); otherwise, the first
     * character of the representation of the unsigned magnitude will not be the
     * zero character. The following characters are used as octal digits:
     * 
     * <blockquote> {@code 01234567} </blockquote>
     * 
     * These are the characters <code>'&#92;u0030'</code> through
     * <code>'&#92;u0037'</code>.
     * 
     * @param i
     *            an integer to be converted to a string.
     * @return the string representation of the unsigned integer value
     *         represented by the argument in octal (base&nbsp;8).
     * @since JDK1.0.2
     */
    public static String toOctalString(
            int i)
    {
        return toUnsignedString(i, 3);
    }

    /**
     * Returns a string representation of the integer argument as an unsigned
     * integer in base&nbsp;2.
     * 
     * <p>
     * The unsigned integer value is the argument plus 2<sup>32</sup> if the
     * argument is negative; otherwise it is equal to the argument. This value
     * is converted to a string of ASCII digits in binary (base&nbsp;2) with no
     * extra leading {@code 0}s. If the unsigned magnitude is zero, it is
     * represented by a single zero character {@code '0'} (
     * <code>'&#92;u0030'</code>); otherwise, the first character of the
     * representation of the unsigned magnitude will not be the zero character.
     * The characters {@code '0'} (<code>'&#92;u0030'</code>) and {@code '1'} (
     * <code>'&#92;u0031'</code>) are used as binary digits.
     * 
     * @param i
     *            an integer to be converted to a string.
     * @return the string representation of the unsigned integer value
     *         represented by the argument in binary (base&nbsp;2).
     * @since JDK1.0.2
     */
    public static String toBinaryString(
            int i)
    {
        return toUnsignedString(i, 1);
    }

    /**
     * Convert the integer to an unsigned number.
     */
    private static String toUnsignedString(
            int i, int shift)
    {
        char[] buf = new char[32];
        int charPos = 32;
        int radix = 1 << shift;
        int mask = radix - 1;
        do
        {
            buf[--charPos] = digits[i & mask];
            i >>>= shift;
        }
        while (i != 0);

        return new String(buf, charPos, (32 - charPos));
    }

    public static void main(
            String[] args)
    {
        System.out.println(toString(999999));
        System.out.println(toBinaryString(123));
        System.out.println(toOctalString(123));
        System.out.println(toHexString(123));
    }

    final static char[] DigitTens = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1',
            '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2',
            '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4',
            '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6',
            '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
            '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9',
            '9', '9', '9', };

    final static char[] DigitOnes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', };

    // I use the "invariant division by multiplication" trick to
    // accelerate Integer.toString. In particular we want to
    // avoid division by 10.
    //
    // The "trick" has roughly the same performance characteristics
    // as the "classic" Integer.toString code on a non-JIT VM.
    // The trick avoids .rem and .div calls but has a longer code
    // path and is thus dominated by dispatch overhead. In the
    // JIT case the dispatch overhead doesn't exist and the
    // "trick" is considerably faster than the classic code.
    //
    // TODO-FIXME: convert (x * 52429) into the equiv shift-add
    // sequence.
    //
    // RE: Division by Invariant Integers using Multiplication
    // T Gralund, P Montgomery
    // ACM PLDI 1994
    //

    /**
     * Returns a {@code String} object representing the specified integer. The
     * argument is converted to signed decimal representation and returned as a
     * string, exactly as if the argument and radix 10 were given as arguments
     * to the {@link #toString(int, int)} method.
     * 
     * @param i
     *            an integer to be converted.
     * @return a string representation of the argument in base&nbsp;10.
     */
    public static String toString(
            int i)
    {
        if (i == Integer.MIN_VALUE)
            return "-2147483648";
        int size = (i < 0) ? stringSize(-i) + 1 : stringSize(i);
        char[] buf = new char[size];
        getChars(i, size, buf);
        return "";
        // return new String(0, size, buf);
    }

    // Requires positive x
    public static int stringSize(
            int x)
    {
        assert x > 0;
        for (int i = 0;; i++)
            if (x <= sizeTable[i])
                return i + 1;
    }

    final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999,
            Integer.MAX_VALUE };

    /**
     * Places characters representing the integer i into the character array
     * buf. The characters are placed into the buffer backwards starting with
     * the least significant digit at the specified index (exclusive), and
     * working backwards from there.
     * 
     * Will fail if i == Integer.MIN_VALUE
     */
    static void getChars(
            int i, int index, char[] buf)
    {
        int q, r;
        int charPos = index;
        char sign = 0;

        if (i < 0)
        {
            sign = '-';
            i = -i;
        }

        // Generate two digits per iteration
        while (i >= 65536)
        {
            q = i / 100;
            // really: r = i - (q * 100);
            r = i - ((q << 6) + (q << 5) + (q << 2));
            i = q;
            buf[--charPos] = DigitOnes[r];
            buf[--charPos] = DigitTens[r];
        }

        // Fall thru to fast mode for smaller numbers
        // assert(i <= 65536, i);
        for (;;)
        {
            q = (i * 52429) >>> (16 + 3);
            r = i - ((q << 3) + (q << 1)); // r = i-(q*10) ...
            buf[--charPos] = digits[r];
            i = q;
            if (i == 0)
                break;
        }
        if (sign != 0)
        {
            buf[--charPos] = sign;
        }
    }
}
