package org.codeexample.algorithms.collected.number.miscs;

/**
 * Double Square Problem Analysis
 * <p>
 * Source: http://www.leetcode.com/2011/01/double-square-problem-analysis.html
 * <p>
 * A double-square number is an integer X which can be expressed as the sum of
 * two perfect squares. For example, 10 is a double-square because 10 = 32 + 12.
 * Your task in this problem is, given X, determine the number of ways in which
 * it can be written as the sum of two squares. For example, 10 can only be
 * written as 32 + 12 (we don’t count 12 + 32 as being different). On the other
 * hand, 25 can be written as 52 + 02 or as 42 + 32.
 * <p>
 * Input You should first read an integer N, the number of test cases. The next
 * N lines will contain N values of X.
 * <p>
 * Constraints 0 = X = 2147483647 1 = N = 100
 * <p>
 */
public class DoubleSquare
{

    /**
     * O(m)
     */
    public static int doubleSquare(
            int m)
    {
        int total = 0;
        int iUpper = (int) Math.sqrt((double) m / 2.0);
        for (int i = 0; i <= iUpper; i++)
        {
            int ii = i * i;
            for (int j = i;; j++)
            {
                int sum = ii + j * j;
                if (sum == m)
                    total++;
                else if (sum > m)
                    break;
            }
        }
        return total;
    }

    /**
     * Consider that: M = x2 + y2,
     * <p>
     * and we have y2 = M – x2.
     * <p>
     * We can easily tell if (M - x2) is a perfect square by taking the square
     * root of it and compare it to its truncated value (deleting its fractional
     * part). If both are equal, then it must be a perfect square, or else it is
     * not. This observation is so subtle that many will miss it,
     */
    public static int doubleSquare2(
            int m)
    {
        int p = (int) Math.sqrt((double) m / 2.0);
        int total = 0;
        for (int i = 0; i <= p; i++)
        {
            double j = Math.sqrt((double) m - i * i);
            if (j - (int) j == 0.0) // might have precision issue,
                total++; // can be resolved using |j-(int)j| == delta
        }
        return total;
    }
}
