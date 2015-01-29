package org.codeexample.algorithms.collected.number.miscs;

public class LocalMinimum {
	public static int findLocalMinimum(int[] input) {
		return findLocalMinimum(input, 0, input.length - 1);

	}

	private static int findLocalMinimum(int[] input, int start, int end) {
		if (end - start < 3) {
			throw new RuntimeException("Not engough data");
		}
		int mid = (start + end) / 2;
		if ((input[mid] <= input[mid - 1]) == (input[mid] <= input[mid + 1])) {
			return mid;
		} else if (input[mid] > input[mid - 1]) {
			return findLocalMinimum(input, start, mid + 1);
		} else {
			return findLocalMinimum(input, mid - 1, end);
		}

	}

	public static void main(String[] args) {
		int[] input = { 8, 5, 2, 6, 9 };
		System.out.println(input[findLocalMinimum(input)]);
	}
}
