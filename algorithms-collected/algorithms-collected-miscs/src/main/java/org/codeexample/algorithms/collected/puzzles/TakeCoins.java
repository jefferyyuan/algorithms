package org.codeexample.algorithms.collected.puzzles;

/**
 * Source: http://www.leetcode.com/2011/02/coins-in-line.html
 * 
 */
public class TakeCoins
{

    private static final int MAX_N = 100;

    private static void printMoves(
            int P[][], int A[], int N)
    {
        int sum1 = 0, sum2 = 0;
        int m = 0, n = N - 1;
        boolean myTurn = true;
        while (m <= n)
        {
            int P1 = P[m + 1][n]; // If take A[m], opponent can get...
            int P2 = P[m][n - 1]; // If take A[n]
            System.out.println((myTurn ? "I" : "You") + " take coin no. ");
            if (P1 <= P2)
            {
                System.out.println(m + 1 + " (" + A[m] + ")");
                m++;
            }
            else
            {
                System.out.println(n + 1 + " (" + A[n] + ")");
                n--;
            }
            System.out.println(myTurn ? ", " : ".\n");
            myTurn = !myTurn;
        }
        System.out.println("\nThe total amount of money (maximum) I get is " + P[0][N - 1] + ".\n");
    }

    /**
     * There are n coins in a line. (Assume n is even). Two players take turns
     * to take a coin from one of the ends of the line until there are no more
     * coins left. The player with the larger amount of money wins.
     * <p>
     * Would you rather go first or second? Does it matter? Assume that you go
     * first, describe an algorithm to compute the maximum amount of money you
     * can win.
     * <p>
     * 
     */
    public static int maxMoney(
            int A[], int N)
    {
        int P[][] = new int[MAX_N][MAX_N];// {0};
        int a, b, c;
        for (int i = 0; i < N; i++)
        {
            for (int m = 0, n = i; n < N; m++, n++)
            {
                assert (m < N);
                assert (n < N);
                a = ((m + 2 <= N - 1) ? P[m + 2][n] : 0);
                b = ((m + 1 <= N - 1 && n - 1 >= 0) ? P[m + 1][n - 1] : 0);
                c = ((n - 2 >= 0) ? P[m][n - 2] : 0);
                P[m][n] = Math.max(A[m] + Math.min(a, b), A[n] + Math.min(b, c));
            }
        }
        printMoves(P, A, N);
        return P[0][N - 1];
    }
}
