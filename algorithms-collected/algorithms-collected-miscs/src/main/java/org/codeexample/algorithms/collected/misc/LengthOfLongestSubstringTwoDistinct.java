package org.codeexample.algorithms.collected.misc;

import java.util.HashSet;

public class LengthOfLongestSubstringTwoDistinct {
	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstringTwoDistinct("aabb"));
	}

	public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int i = 0, j = -1, maxLen = 0;
        for (int k = 1; k < s.length(); k++) {
            if (s.charAt(k) == s.charAt(k - 1)) continue;
            if (j >= 0 && s.charAt(j) != s.charAt(k)) {
                maxLen = Math.max(k - i, maxLen);
                i = j + 1; 
            }
            j = k - 1;  
        }
        return Math.max(s.length() - i, maxLen);
    }
	
	// not work
	public static String subString(String s) {
		// checking

		char[] arr = s.toCharArray();
		int max = 0;
		int j = 0;
		int m = 0, n = 0;

		HashSet<Character> set = new HashSet<Character>();
		set.add(arr[0]);

		for (int i = 1; i < arr.length; i++) {
			if (set.add(arr[i])) {
				if (set.size() > 2) {
					String str = s.substring(j, i);

					// keep the last character only
					set.clear();
					set.add(arr[i - 1]);

					if ((i - j) > max) {
						m = j;
						n = i - 1;
						max = i - j;
					}

					j = i - helper(str);
				}
			}
		}

		return s.substring(m, n + 1);
	}

	// This method returns the length that contains only one character from
	// right side.
	public static int helper(String str) {
		// null & illegal checking here
		if (str == null) {
			return 0;
		}

		if (str.length() == 1) {
			return 1;
		}

		char[] arr = str.toCharArray();
		char p = arr[arr.length - 1];
		int result = 1;

		for (int i = arr.length - 2; i >= 0; i--) {
			if (p == arr[i]) {
				result++;
			} else {
				break;
			}
		}

		return result;
	}
}
