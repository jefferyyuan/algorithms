package org.codeexample.algorithms.collected.string.miscs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindMaxKCharSubstring {

	public static void main(String[] args) {
		System.out.println(findMaxKCharSubstring("abcccccaaddddeeee", 3));
	}

	public static int findMaxKCharSubstring(String s, int k) {
		Map<Character, Integer> charNum = new HashMap<Character, Integer>();
		int start = 0;
		int end = 0;
		// int charType = 2; //can be k
		int maxLen = 0;
		while (end < s.length()) {
			char cur = s.charAt(end);
			// if this char is in substring already, then increase its number
			if (charNum.containsKey(cur)) {
				charNum.put(cur, charNum.get(cur) + 1);
			} else {
				charNum.put(cur, 1);
				if (charNum.size() > k) {
					// We need eliminate another char in substring to maintain
					// the feasibility of the substring.
					while (charNum.get(s.charAt(start)) > 0
							&& charNum.size() > k) {
						int newCount = charNum.get(s.charAt(start)) - 1;
						if (newCount > 0) {
							charNum.put(s.charAt(start), newCount);
						} else {
							charNum.remove(s.charAt(start));
						}
						start++;
					}
				}
			}
			if (maxLen < end - start + 1) {
				maxLen = end - start + 1;
			}
			end++;
		}
		return maxLen;
	}

	public int lengthOfLongestSubstringTwoDistinctOn2(String s) {
		Map<Character, Integer> charNum = new HashMap<Character, Integer>();
		int start = 0;
		int end = 0;
		int charType = 2; // can be k
		int maxLen = 0;
		while (end < s.length()) {
			char cur = s.charAt(end);
			// if this char is in substring already, then increase its number
			if (charNum.containsKey(cur)) {
				charNum.put(cur, charNum.get(cur) + 1);
			} else {
				charNum.put(cur, 1);
				if (charType > 0) {
					charType--;
				} else {
					// We need eliminate another char in substring to maintain
					// the feasibility of the substring.
					while (charNum.get(s.charAt(start)) > 1) {
						charNum.put(s.charAt(start),
								charNum.get(s.charAt(start)) - 1);
						start++;
					}
					charNum.remove(s.charAt(start));
					start++;
				}
			}
			if (maxLen < end - start + 1) {
				maxLen = end - start + 1;
			}
			end++;
		}
		return maxLen;
	}

	// WRONG NOT WORK
	static int wfindMaxKCharSubstring(String s, int k) {
		char[] arr = s.toCharArray();
		int max = 0;

		if (arr.length > 0) {
			Set<Character> set = new HashSet<Character>();

			char c = arr[0];
			set.add(c);

			int i = 0;// index
			int currentCount = 0;

			while (i < arr.length) {
				char temp = arr[i];
				if (!set.contains(temp)) {

					if (set.size() < k) {
						set.add(temp);
						currentCount++;
						i++;
					} else {
						max = Math.max(currentCount, max);

						// reset
						currentCount = 0;
						i--;

						char keep = arr[i];// keep the right most one
						set.clear();
						set.add(keep);
						set.add(temp);

						while (i >= 1) {
							if (arr[i - 1] == keep) {
								i--;
							} else {
								break;
							}
						}
					}
				} else {
					currentCount++;
					i++;
				}
			}

			if (currentCount > max) {
				max = currentCount;
			}
		}

		return max;
	}
}
