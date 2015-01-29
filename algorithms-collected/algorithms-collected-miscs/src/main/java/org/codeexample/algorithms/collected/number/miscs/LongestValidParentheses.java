package org.codeexample.algorithms.collected.number.miscs;

import java.util.Stack;

public class LongestValidParentheses {

	public static void main(String[] args) {
		String s = ")()()";
		System.out.println(longestValidParentheses2(s));
		System.out.println(longestValidParentheses(s));
	}

	public static int longestValidParentheses2(String s) {
		// Start typing your Java solution below
		// DO NOT write main() function
		int n = s.length();
		int[] dp = new int[n];
		java.util.Arrays.fill(dp, 0);
		int max = 0;
		for (int i = n - 2; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				int j = i + 1 + dp[i + 1];
				if (j < n && s.charAt(j) == ')') {
					dp[i] = dp[i + 1] + 2;
					int k = 0;
					if (j + 1 < n) {
						k = dp[j + 1];
					}
					dp[i] += k;
				}
				max = Math.max(max, dp[i]);
			}
		}
		return max;
	}

	// 384ms for 229 test cases
	public static int longestValidParentheses(String s) {
		if (s == null || s.length() == 0)
			return 0;
		int longestLength = 0; // Length of the longest valid parentheses
		int start = 0; // The start index of the possibly longest valid
						// parentheses
		Stack<Integer> stack = new Stack<Integer>();
		// One-pass scan
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') { // Opening parenthesis
				stack.push(i); // Push its index
			} else { // Closing parenthesis
				if (stack.empty()) { // No opening parenthesis to match
					start = i + 1; // i+1 is the start of next possibly LVP
				} else {
					stack.pop(); // The index of the opening parenthesis matched
									// by s[i]
					if (stack.empty()) // s[start...i] is matched
						longestLength = Math.max(longestLength, i - start + 1);
					else
						// s[stack.peek()] is unmatched; s[stack.peek()+1...i]
						// is matched
						longestLength = Math.max(longestLength,
								i - stack.peek());
				}
			}
		}

		return longestLength;
	}
}