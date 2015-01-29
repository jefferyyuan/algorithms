package org.codeexample.algorithms.collected.string.miscs;

public class KMPMatch {

	// private String text;
	// private String pattern;
	// private int[] failure;
	// private int matchPoint;

	/**
	 * Finds the first occurrence of the pattern in the text.
	 */

	public static void main(String[] args) {
		System.out.println(match("ABC ABCDAB ABCDABCDABDE", "ABCDABD"));
	}

	public static boolean match(String text, String pattern) {
		int j = 0;
		if (text.length() == 0)
			return false;
		int[] failure = computeFailure(pattern);
		int matchPoint = 0;
		for (int i = 0; i < text.length(); i++) {
			while (j > 0 && pattern.charAt(j) != text.charAt(i)) {
				j = failure[j - 1];
			}
			if (pattern.charAt(j) == text.charAt(i)) {
				j++;
			}
			if (j == pattern.length()) {
				matchPoint = i - pattern.length() + 1;
				return true;
			}
		}
		return false;
	}

	/**
	 * Computes the failure function using a boot-strapping process, where the
	 * pattern is matched against itself.
	 * 
	 * @return
	 */
	private static int[] computeFailure(String pattern) {
		int[] failure = new int[pattern.length()];
		int j = 0;
		for (int i = 1; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
				j = failure[j - 1];
			}
			if (pattern.charAt(j) == pattern.charAt(i)) {
				j++;
			}
			failure[i] = j;
		}
		return failure;
	}
}