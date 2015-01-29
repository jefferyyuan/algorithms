package org.codeexample.algorithms.collected.misc;

import java.util.LinkedList;

public class GetTranslationCountAlg {

	

	public static int GetTranslationCount(int number) {
		if (number <= 0) {
			return 0;
		}

		String numberInString = String.valueOf(number);
		return GetTranslationCount(numberInString.toCharArray());
	}

	private static int GetTranslationCount(char[] number) {
		LinkedList<Integer> alist = new LinkedList<>();
		alist.push(1);

		int length = number.length;
		int[] counts = new int[length];

		for (int i = length - 1; i >= 0; --i) {
			int count = 0;
			if (number[i] >= '1' && number[i] <= '9') {
				if (i < length - 1) {
					count += counts[i + 1];
				} else {
					count += 1;
				}
			}

			if (i < length - 1) {
				int digit1 = number[i] - '0';
				int digit2 = number[i + 1] - '0';
				int converted = digit1 * 10 + digit2;
				if (converted >= 10 && converted <= 26) {
					if (i < length - 2) {
						count += counts[i + 2];
					} else {
						count += 1;
					}
				}
			}

			counts[i] = count;
		}

		return counts[0];
	}
}
