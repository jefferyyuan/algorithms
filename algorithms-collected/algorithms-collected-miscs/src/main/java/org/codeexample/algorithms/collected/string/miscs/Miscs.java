package org.codeexample.algorithms.collected.string.miscs;

public class Miscs
{

    /**
     * Source:
     * http://www.leetcode.com/2011/05/longest-substring-without-repeating
     * -characters.html
     * <p>
     * Given a string, find the length of the longest substring without
     * repeating characters. For example, the longest substring without
     * repeating letters for “abcabcbb” is “abc”, which the length is 3. For
     * “bbbbb” the longest substring is “b”, with the length of 1.
     * <p>
     */
    public static int lengthOfLongestSubstring(
            String s)
    {
        int n = s.length();
        int i = 0, j = 0;
        int maxLen = 0;
        boolean exist[] = new boolean[256];// { false };
        for (int tmp = 0; tmp < exist.length; tmp++)
        {
            exist[tmp] = false;
        }
        while (j < n)
        {
            if (exist[s.charAt(j)])
            {
                maxLen = Math.max(maxLen, j - i);
                while (s.charAt(i) != s.charAt(j))
                {
                    exist[s.charAt(i)] = false;
                    i++;
                }
                i++;
                j++;
            }
            else
            {
                exist[s.charAt(j)] = true;
                j++;
            }
        }
        maxLen = Math.max(maxLen, n - i);
        return maxLen;
    }

}
