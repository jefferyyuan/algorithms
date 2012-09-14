package org.codeexample.algorithms.collected.number.miscs;

/**
 * Source:
 * http://www.leetcode.com/2011/11/longest-palindromic-substring-part-i.html
 * 
 */
public class LongestPalindromicSubstring
{

    // Brute force solution, O(N3):
    // The obvious brute force solution is to pick all possible starting and
    // ending positions for a substring, and verify if it is a palindrome. There
    // are a total of C(N, 2) such substrings (excluding the trivial solution
    // where a character itself is a palindrome).
    //
    // Since verifying each substring takes O(N) time, the run time complexity
    // is O(N3).

    /**
     * Dynamic programming solution, O(N2) time and O(N2) space: To improve over
     * the brute force solution from a DP approach, first think how we can avoid
     * unnecessary re-computation in validating palindromes. Consider the case
     * “ababa”. If we already knew that “bab” is a palindrome, it is obvious
     * that “ababa” must be a palindrome since the two left and right end
     * letters are the same.
     * <p>
     * Stated more formally below: Define P[ i, j ] ← true iff the substring Si
     * … Sj is a palindrome, otherwise false.
     * <p>
     * Therefore, P[ i, j ] ← ( P[ i+1, j-1 ] and Si = Sj )
     * <p>
     * The base cases are: P[ i, i ] ← true P[ i, i+1 ] ← ( Si = Si+1 )
     * <p>
     * This yields a straight forward DP solution, which we first initialize the
     * one and two letters palindromes, and work our way up finding all three
     * letters palindromes, and so on…
     * <p>
     * This gives us a run time complexity of O(N2) and uses O(N2) space to
     * store the table.
     */
    public static String longestPalindromeDP(
            String s)
    {
        int n = s.length();
        int longestBegin = 0;
        int maxLen = 1;
        boolean table[][] = new boolean[s.length()][s.length()];
        // initialize all to false
        for (int i = 0; i < n; i++)
        {
            table[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++)
        {
            if (s.charAt(i) == s.charAt(i + 1))
            {
                table[i][i + 1] = true;
                longestBegin = i;
                maxLen = 2;
            }
        }
        for (int len = 3; len <= n; len++)
        {
            for (int i = 0; i < n - len + 1; i++)
            {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && table[i + 1][j - 1])
                {
                    table[i][j] = true;
                    longestBegin = i;
                    maxLen = len;
                }
            }
        }
        return s.substring(longestBegin, maxLen);
    }

    private static String expandAroundCenter(
            String s, int c1, int c2)
    {
        int l = c1, r = c2;
        int n = s.length();
        while (l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r))
        {
            l--;
            r++;
        }
        return s.substring(l + 1, r - l - 1);
    }

    /**
     * A simpler approach, O(N2) time and O(1) space。
     * <p>
     * We observe that a palindrome mirrors around its center. Therefore, a
     * palindrome can be expanded from its center, and there are only 2N-1 such
     * centers.
     * <p>
     */
    public static String longestPalindromeSimple(
            String s)
    {
        int n = s.length();
        if (n == 0)
            return "";
        // a single char itself is a palindrome
        String longest = s.substring(0, 1);
        for (int i = 0; i < n - 1; i++)
        {
            String p1 = expandAroundCenter(s, i, i);
            if (p1.length() > longest.length())
                longest = p1;

            String p2 = expandAroundCenter(s, i, i + 1);
            if (p2.length() > longest.length())
                longest = p2;
        }
        return longest;
    }
}
