package org.codeexample.algorithms.collected.string.search;

import java.util.Arrays;

public class Test {

	private static class KnuthMorrisPratt {
		public int[] prekmpOpt(String pattern) {
			int[] next = new int[pattern.length()];
			int i = 0, j = -1;
			next[0] = -1;
			while (i < pattern.length() - 1) {
				while (j >= 0 && pattern.charAt(i) != pattern.charAt(j))
					j = next[j];
				i++;
				j++;
				next[i] = j;
			}
			System.out.println(Arrays.toString(next));
			return next;
		}

		public int[] prekmp(String pattern) {
			int[] next = new int[pattern.length()];
			next[0] = -1;
			next[1] = 0;
			int pos = 2, cnd = 0;

			while (pos < pattern.length()) {
				if (pattern.charAt(pos - 1) == pattern.charAt(cnd)) {
					cnd++;
					next[pos] = cnd;
				} else {
					cnd = next[cnd];
					while (cnd > 0
							&& pattern.charAt(pos - 1) != pattern.charAt(cnd))
						cnd = next[cnd];

					if (cnd < 0) {
						next[pos] = 0;
						cnd = 0;
					} else {
						next[pos] = cnd;
					}
				}

				pos++;
			}
			System.out.println(Arrays.toString(next));
			return next;
		}

		public int[] prekmpx(String pattern) {
			int[] next = new int[pattern.length()];
			next[0] = -1;
			next[1] = 0;
			int pos = 2, cnd = 0;

			while (pos < pattern.length()) {
				while (cnd > 0
						&& pattern.charAt(pos - 1) != pattern.charAt(cnd))
					cnd = next[cnd];
				if (cnd == 0) {
					next[pos] = 0;
				} else {
					cnd++;
					next[pos] = cnd;
				}

				pos++;
			}
			System.out.println(Arrays.toString(next));
			return next;
		}

		public int kmp(String text, String pattern) {
			int[] next = prekmp(pattern);
			int textStart = 0, patternStart = 0;
			while (textStart + patternStart < text.length()) {
				if (text.charAt(textStart + patternStart) == pattern
						.charAt(patternStart)) {
					if (patternStart == pattern.length() - 1) {
						return textStart;
					}
					patternStart++;
				} else {
					if (next[patternStart] > -1) {
						textStart = textStart + patternStart
								- next[patternStart];
						patternStart = next[patternStart];
					} else {
						textStart++;
						patternStart = 0;
					}
				}
			}
			return -1;
		}

		public int kmp2(String text, String pattern) {
			int[] next = prekmp(pattern);
			int i = 0, j = 0;
			while (i < text.length()) {
				while (j >= 0 && text.charAt(i) != pattern.charAt(j))
					j = next[j];
				i++;
				j++;
				if (j == pattern.length())
					return i - pattern.length();
			}
			return -1;
		}

	}

	public static void main(String[] args) {
		KnuthMorrisPratt k = new KnuthMorrisPratt();
		String text = "abxababaay";
		String pattern = "ababaa";

		int first_occur_position = k.kmp(text, pattern);
		System.out.println("The text '" + pattern + "' is first found on the "
				+ first_occur_position + " position.");
	}
}