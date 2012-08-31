package org.codeexample.algorithms.collected.spellchecker;

/**
 * Source: http://www.ibm.com/developerworks/java/library/j-jazzy/
 * <p>
 * String similarity algorithms Distance is the number of steps it takes to
 * transform one word into another, provided that you change only one letter at
 * a time and use actual dictionary words for each step The puzzle distance
 * defined above is unsuitable for at least one good reason: A misspelled word
 * is not always one letter away from a correctly spelled one. For instance,
 * there are no "stepping stones" from the misspelling puzzel to any correctly
 * spelled English word.
 * <p>
 * The Dynamic Programming algorithm The Dynamic Programming algorithm is
 * essentially a brute-force method that considers all the different ways of
 * transforming a source word to a target to find the least cost, or distance
 * between the two. Levenshtein distance, a particular implementation of the
 * Dynamic Programming algorithm, permits three types of operation for
 * transforming the source word x to the target word y: substitution, deletion,
 * insertion.
 * <p>
 * Each operation has a certain cost, and the total distance is the smallest
 * total cost for transforming word x to word y. It is intuitively plausible
 * that an algorithm based on these operations would work well for spelling
 * correction, since typos are nothing more than these operations interpreted as
 * keying errors. (In fact, a Levenshtein distance is also known as an edit
 * distance.)
 * <p>
 * To start the algorithm you fill in the first row, which corresponds to an
 * empty source word, so it is the cost of inserting 0, 1, ..., j letters.
 * Similarly the first column corresponds to an empty target word, so it is the
 * cost of deleting 0, 1, ..., i letters.
 * <p>
 * Next, you calculate the values in each remaining cell by considering its
 * three neighbors: above, to the left, and diagonally upward and to the left.
 * Left=Min( Diagonal + substitution cost, Above + delete cost, Left + insert
 * cost )
 * <p>
 * A limitation of both of these implementations is that they do not scale to
 * large strings, since the storage requirements are O(mn), where m and n are
 * the length of the source and target words, respectively. If you need only to
 * compute the distance and not alignments, as is usually the case, it is easy
 * to reduce this to O(n), since only the previous row is required to compute
 * the next.
 */
public class LevenshteinDistanceMetric
{
    /**
     * Calculates the distance between Strings x and y using the <b>Dynamic
     * Programming</b> algorithm.
     */
    public final int distance(
            String x, String y)
    {

        int m = x.length();
        int n = y.length();

        int[][] T = new int[m + 1][n + 1];

        T[0][0] = 0;
        for (int j = 0; j < n; j++)
        {
            T[0][j + 1] = T[0][j] + ins(y, j);
        }
        for (int i = 0; i < m; i++)
        {
            T[i + 1][0] = T[i][0] + del(x, i);
            for (int j = 0; j < n; j++)
            {
                T[i + 1][j + 1] = min(T[i][j] + sub(x, i, y, j), T[i][j + 1] + del(x, i),
                        T[i + 1][j] + ins(y, j));
            }
        }

        return T[m][n];
    }

    private int sub(
            String x, int xi, String y, int yi)
    {
        return x.charAt(xi) == y.charAt(yi) ? 0 : 1;
    }

    private int ins(
            String x, int xi)
    {
        return 1;
    }

    private int del(
            String x, int xi)
    {
        return 1;
    }

    private int min(
            int a, int b, int c)
    {
        return Math.min(Math.min(a, b), c);
    }
}