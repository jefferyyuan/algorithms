package org.codeexample.algorithms.collected.number.miscs;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class BigNumber {

	public static void array_multiplication(int A[], int OUTPUT[], int n) {
		int left = 1;
		int right = 1;
		for (int i = 0; i < n; i++)
			OUTPUT[i] = 1;
		for (int i = 0; i < n; i++) {
			OUTPUT[i] *= left;
			OUTPUT[n - 1 - i] *= right;
			left *= A[i];
			right *= A[n - 1 - i];
		}
	}

	public static void main(String args[]) {
		int A[] = { 4, 3, 2, 1, 2 };
		int[] OUTPUT = new int[5];
		array_multiplication(A, OUTPUT, A.length);
		System.out.println(Arrays.toString(OUTPUT));
	}

	public static void m1() {
		Scanner scanner = new Scanner(System.in);
		int sum[] = new int[1010];

		BigInteger factorial = BigInteger.ONE;
		for (int i = 1; i <= 1000; i++) {
			factorial = factorial.multiply(BigInteger.valueOf(i));
			String value = factorial.toString();
			for (int j = 0; j < value.length(); j++) {
				sum[i] += value.charAt(j) - '0';
			}
		}

		Arrays.toString(sum);
		// while (scanner.hasNext()) {
		// System.out.println(sum[scanner.nextInt()]);
		// }
	}
}
