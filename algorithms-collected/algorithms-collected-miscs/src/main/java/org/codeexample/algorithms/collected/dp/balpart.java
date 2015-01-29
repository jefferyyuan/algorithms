package org.codeexample.algorithms.collected.dp;

import java.io.*;
import java.util.*;

public class balpart {
	public static void main(String[] args) throws IOException {

		int sum = 0;
		System.out.println("Enter the number of array elements");
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int[] array = new int[N];
		System.out.println("Enter the Positive elements of the array");
		for (int member = 0; member < N; member++) {
			array[member] = input.nextInt();
			sum += array[member];
		}

		boolean[] sol = new boolean[sum / 2 + 1];

		sol[0] = true;// empty array
		for (int i : array) {
			for (int j = sum / 2; j >= i; j--) {
				if (sol[j - i])
					sol[j] = true;
			}
		}

		int halfsumcloser = sum / 2;
		for (; !sol[halfsumcloser]; halfsumcloser--)
			;
		System.out.println("The Minimum sum After partioning the array is :"
				+ ((sum - halfsumcloser) - halfsumcloser));

	}
}