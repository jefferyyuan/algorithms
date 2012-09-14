package org.codeexample.algorithms.collected.number.miscs;

public class Miscs
{

    /**
     * Determine whether an integer is a palindrome. Do this without extra
     * space.
     * <p>
     * Source: http://www.leetcode.com/2012/01/palindrome-number.html
     */
    public static boolean isPalindrome(
            int x)
    {
        if (x < 0)
            return false;
        int div = 1;
        while (x / div >= 10)
        {
            div *= 10;
        }
        while (x != 0)
        {
            int l = x / div;
            int r = x % 10;
            if (l != r)
                return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    /**
     * A better way, O(k): There is an improvement from the above method, thanks
     * to readers who suggested this. (See comments below by Martin for an
     * implementation). Using two pointers, you can traverse both arrays without
     * actually merging them, thus without the extra space. Both pointers are
     * initialized to point to head of A and B respectively, and the pointer
     * that has the larger smaller (thanks to a reader for this correction) of
     * the two is incremented one step. The k-th smallest is obtained by
     * traversing a total of k steps.
     * <p>
     * The best solution, but non-trivial, O(lg m + lg n):
     * <p>
     * We try to approach this tricky problem by comparing middle elements of A
     * and B, which we identify as Ai and Bj. If Ai is between Bj and Bj-1, we
     * have just found the i+j+1 smallest element. Why? Therefore, if we choose
     * i and j such that i+j = k-1, we are able to find the k-th smallest
     * element. This is an important invariant that we must maintain for the
     * correctness of this algorithm.
     * <p>
     * Summarizing the above, Maintaining the invariant i + j = k – 1,
     * <p>
     * If Bj-1 < Ai < Bj, then Ai must be the k-th smallest, or else if Ai-1 <
     * Bj < Ai, then Bj must be the k-th smallest.
     */
    public static int findKthSmallest(
            int A[], int m, int B[], int n, int k)
    {
        assert (m >= 0);
        assert (n >= 0);
        assert (k > 0);
        assert (k <= m + n);

        int i = (int) ((double) m / (m + n) * (k - 1));
        int j = (k - 1) - i;

        assert (i >= 0);
        assert (j >= 0);
        assert (i <= m);
        assert (j <= n);
        // invariant: i + j = k-1
        // Note: A[-1] = -INF and A[m] = +INF to maintain invariant
        int Ai_1 = ((i == 0) ? Integer.MIN_VALUE : A[i - 1]);
        int Bj_1 = ((j == 0) ? Integer.MIN_VALUE : B[j - 1]);
        int Ai = ((i == m) ? Integer.MAX_VALUE : A[i]);
        int Bj = ((j == n) ? Integer.MAX_VALUE : B[j]);

        if (Bj_1 < Ai && Ai < Bj)
            return Ai;
        else if (Ai_1 < Bj && Bj < Ai)
            return Bj;

        assert ((Ai > Bj && Ai_1 > Bj) || (Ai < Bj && Ai < Bj_1));

        return -1;
        // if none of the cases above, then it is either:
        // if (Ai < Bj)
        // // exclude Ai and below portion
        // // exclude Bj and above portion
        // return findKthSmallest(A + i + 1, m - i - 1, B, j, k - i - 1);
        // else
        // /* Bj < Ai */
        // // exclude Ai and above portion
        // // exclude Bj and below portion
        // return findKthSmallest(A, i, B + j + 1, n - j - 1, k - j - 1);
    }

    private static double findMedianBaseCase(
            int med, int C[], int n)
    {
        if (n == 1)
            return (med + C[0]) / 2.0;

        if (n % 2 == 0)
        {
            int a = C[n / 2 - 1], b = C[n / 2];
            if (med <= a)
                return a;
            else if (med <= b)
                return med;
            else
                /* med > b */
                return b;
        }
        else
        {
            int a = C[n / 2 - 1], b = C[n / 2], c = C[n / 2 + 1];
            if (med <= a)
                return (a + b) / 2.0;
            else if (med <= c)
                return (med + b) / 2.0;
            else
                /* med > c */
                return (b + c) / 2.0;
        }
    }

    private static double findMedianBaseCase2(
            int med1, int med2, int C[], int n)
    {
        if (n % 2 == 0)
        {
            int a = (((n / 2 - 2) >= 0) ? C[n / 2 - 2] : Integer.MIN_VALUE);
            int b = C[n / 2 - 1], c = C[n / 2];
            int d = (((n / 2 + 1) <= n - 1) ? C[n / 2 + 1] : Integer.MAX_VALUE);
            if (med2 <= b)
                return (b + Math.max(med2, a)) / 2.0;
            else if (med1 <= b)
                return (b + Math.min(med2, c)) / 2.0;
            else if (med1 >= c)
                return (c + Math.min(med1, d)) / 2.0;
            else if (med2 >= c)
                return (c + Math.max(med1, b)) / 2.0;
            else
                /* a < med1 <= med2 < b */
                return (med1 + med2) / 2.0;
        }
        else
        {
            int a = C[n / 2 - 1], b = C[n / 2], c = C[n / 2 + 1];
            if (med1 >= b)
                return Math.min(med1, c);
            else if (med2 <= b)
                return Math.max(med2, a);
            else
                /* med1 < b < med2 */
                return b;
        }
    }

    private static double findMedianSingleArray(
            int A[], int n)
    {
        assert (n > 0);
        return ((n % 2 == 1) ? A[n / 2] : (A[n / 2 - 1] + A[n / 2]) / 2.0);
    }

    /**
     * Median of Two Sorted Arrays
     * <p>
     */
    public static double findMedianSortedArrays(
            int A[], int m, int B[], int n)
    {
        assert (m + n >= 1);
        if (m == 0)
            return findMedianSingleArray(B, n);
        else if (n == 0)
            return findMedianSingleArray(A, m);
        else if (m == 1)
            return findMedianBaseCase(A[0], B, n);
        else if (n == 1)
            return findMedianBaseCase(B[0], A, m);
        else if (m == 2)
            return findMedianBaseCase2(A[0], A[1], B, n);
        else if (n == 2)
            return findMedianBaseCase2(B[0], B[1], A, m);

        int i = m / 2, j = n / 2, k;
        if (A[i] <= B[j])
        {
            k = ((m % 2 == 0) ? Math.min(i - 1, n - j - 1) : Math.min(i, n - j - 1));
            assert (k > 0);
            // return findMedianSortedArrays(A + k, m - k, B, n - k);
            return -1;
        }
        else
        {
            k = ((n % 2 == 0) ? Math.min(m - i - 1, j - 1) : Math.min(m - i - 1, j));
            assert (k > 0);
            // return findMedianSortedArrays(A, m - k, B + k, n - k);
            return -1;
        }
    }
}
