package org.codeexample.algorithms.collected.number.miscs;

import java.util.Arrays;
import java.util.HashSet;

public class LongestTwoUnique {
	// We create a set of length 256 to keep the last index from the left.
	// Whenever we reach a new character and count is still less than or equal
	// to 2 we check if the current substring length is greater than the
	// previous result. If it is we update the result as the current longest
	// substring.
	public static String longestTwoUnique(String s) {
		char str[] = s.toCharArray();
		int[] set = new int[256];
		Arrays.fill(set, -1);
		int i = 0, j = 0;
		String res = "";
		int count = 0;
		while (j < s.length()) {
			if (set[str[j]] == -1) {
				set[str[j]] = j;
				count++;
				if (res.length() <= j - i)
					res = s.substring(i, j);
				if (count > 2) {
					count--;
					int nextI = set[str[i]];
					set[str[i]] = -1;
					i = nextI + 1;
				}
			} else {
				set[str[j]] = j;
				if (res.length() <= j - i)
					res = s.substring(i, j);
			}
			j++;
		}
		if (count <= 2 && res.length() <= j - i)
			res = s.substring(i, j);
		return res;
	}

	public static void main(String args[]) {
		System.out.println("longest string: "
				+ subString("aabbccccceeedddddddd"));

	}

	// /http://www.programcreek.com/2013/02/longest-substring-which-contains-2-unique-characters/
	// not right
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