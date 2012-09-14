package org.codeexample.algorithms.collected.puzzles;

/**
 * Source: http://www.leetcode.com/2010/11/unique-paths.html
 * <p>
 * A robot is located at the top-left corner of a m x n grid (marked ‘Start’ in
 * the diagram below). The robot can only move either down or right at any point
 * in time. The robot is trying to reach the bottom-right corner of the grid
 * (marked ‘Finish’ in the diagram below). How many possible unique paths are
 * there?
 */
public class UniquePaths
{

    /**
     * Backtracking Solution
     */
    public static int backtrack(
            int r, int c, int m, int n)
    {
        if (r == m && c == n)
            return 1;
        if (r > m || c > n)
            return 0;

        return backtrack(r + 1, c, m, n) + backtrack(r, c + 1, m, n);
    }

    private static final int M_MAX = 100;
    private static final int N_MAX = 100;

    /**
     * Although the above backtracking solution is easy to code, it is very
     * inefficient in the sense that it recalculates the same solution for a
     * grid over and over again. By caching the results, we prevent
     * recalculation and only calculates when necessary. Here, we are using a
     * dynamic programming (DP) technique called memoization.
     */
    public static int backtrack2(
            int r, int c, int m, int n, int mat[][])
    {
        if (r == m && c == n)
            return 1;
        if (r > m || c > n)
            return 0;

        if (mat[r + 1][c] == -1)
            mat[r + 1][c] = backtrack2(r + 1, c, m, n, mat);
        if (mat[r][c + 1] == -1)
            mat[r][c + 1] = backtrack2(r, c + 1, m, n, mat);

        return mat[r + 1][c] + mat[r][c + 1];
    }

    int bt(int m, int n)
    {
        // TODO
        int mat[][] = new int[M_MAX + 2][N_MAX + 2];
        for (int i = 0; i < M_MAX + 2; i++)
        {
            for (int j = 0; j < N_MAX + 2; j++)
            {
                mat[i][j] = -1;
            }
        }
        return backtrack2(1, 1, m, n, mat);
    }

    public static int backtrack3(
            int m, int n)
    {
        // TODO
        int mat[][] = new int[M_MAX + 2][N_MAX + 2];
        mat[m][n + 1] = 1;

        for (int r = m; r >= 1; r--)
            for (int c = n; c >= 1; c--)
                mat[r][c] = mat[r + 1][c] + mat[r][c + 1];

        return mat[1][1];
    }
}
