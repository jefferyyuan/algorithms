package org.codeexample.algorithms.collected.number.miscs;

import java.util.HashMap;
import java.util.HashSet;

public class LengthOfLongestSubstring {
	public static void main(String[] args) {
//		System.out.println(lengthOfLongestSubstring3("dabcabcbb"));

		System.out.println(lengthOfLongestSubstring0("dabcabcbb"));
	}

	public static int lengthOfLongestSubstring0(String s) {

		char[] arr = s.toCharArray();
		int pre = 0;

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], i);
			} else {
				pre = pre > map.size() ? pre : map.size();
				// clean the first arr[i]
				i = map.get(arr[i]);
				map.clear();
			}
		}

		return Math.max(pre, map.size());
	}
	
	public static int lengthOfLongestSubstring2(String s) {

		char[] arr = s.toCharArray();
		int pre = 0;

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], i);
			} else {
				pre = pre > map.size() ? pre : map.size();
				i = map.get(arr[i]);
				map.clear();
			}
		}

		return Math.max(pre, map.size());
	}

	public static int lengthOfLongestSubstring3(String s) {
		int max = 0;
		HashSet<Character> set = new HashSet<Character>();
		int candidateStartIndex = 0;
		for (int iter = 0; iter < s.length(); ++iter) {
			char c = s.charAt(iter);
			if (set.contains(c)) {
				max = Math.max(max, iter - candidateStartIndex);
				while (s.charAt(candidateStartIndex) != s.charAt(iter)) {
					set.remove(s.charAt(candidateStartIndex));
					candidateStartIndex++;
				}
				candidateStartIndex++;
			} else {
				set.add(c);
			}
		}
		max = Math.max(max, s.length() - candidateStartIndex);
		return max;
	}

	public static int lengthOfLongestSubstring1(String s) {
		boolean[] flag = new boolean[256];

		int result = 0;
		int j = 0;
		char[] arr = s.toCharArray();

		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if (flag[c]) {
				result = Math.max(result, i - j);
				for (int k = j; k < i; k++) {
					if (arr[k] == c) {
						j = k + 1;
						break;
					}
					flag[arr[k]] = false;
				}
			} else {
				flag[c] = true;
			}
		}

		result = Math.max(arr.length - j, result);

		return result;
	}

}
