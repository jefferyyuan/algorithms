package org.codeexample.algorithms.collected.number.miscs;

import java.util.Arrays;

public class FirstMissingPositive {

	public static void main(String[] args) {
		int[] a = // { 3, 4, -1, 1 , 100};
		{ 0, 10, 2, -10, -20 };
		System.out.println(firstMissingPositive(a));
	}

	//
	// int segregate(int arr[], int size) {
	// int j = 0, i;
	// for (i = 0; i < size; i++) {
	// if (arr[i] <= 0) {
	// swap(arr[i], arr[j]);
	// j++; // increment count of non-positive integers
	// }
	// }
	//
	// return j;
	// }
	//
	// /*
	// * Find the smallest positive missing number in an array that contains all
	// * positive integers
	// */
	// int findMissingPositive(int arr[], int size) {
	// int i;
	//
	// // Mark arr[i] as visited by making arr[arr[i] - 1] negative. Note that
	// // 1 is subtracted because index start from 0 and positive numbers start
	// // from 1
	// for (i = 0; i < size; i++) {
	// if (Math.abs(arr[i]) - 1 < size && arr[Math.abs(arr[i]) - 1] > 0)
	// arr[Math.abs(arr[i]) - 1] = -arr[Math.abs(arr[i]) - 1];
	// }
	//
	// // Return the first index value at which is positive
	// for (i = 0; i < size; i++)
	// if (arr[i] > 0)
	// return i + 1; // 1 is added becuase indexes start from 0
	//
	// return size + 1;
	// }
	//
	// /*
	// * Find the smallest positive missing number in an array that contains
	// both
	// * positive and negative integers
	// */
	// int findMissing(int arr[], int size) {
	// // First separate positive and negative numbers
	// int shift = segregate(arr, size);
	//
	// // Shift the array and call findMissingPositive for
	// // positive part
	// return findMissingPositive(arr, shift);
	// }

	public static int firstMissingPositive(int[] A) {
		if (A == null || A.length == 0)
			return 1;
		int n = A.length;
		// Put A[i] (>0) at index A[i]-1
		for (int i = 0; i < n; i++) {
			if (A[i] <= n && A[i] > 0 && A[A[i] - 1] != A[i]) {
				// Swap A[i] and A[A[i]-1]; note the sequence
				int temp = A[A[i] - 1];
				A[A[i] - 1] = A[i];
				A[i] = temp;
				// Process A[i] in the next round
				i--;
			}
		}
		System.out.println(Arrays.toString(A));

		// Check the first occurrence of A[i] != i+1
		// If exists, i+1 is the first missing positive; otherwise, the numbers
		// are the first n positve numbers, the first missing one is n+1
		for (int i = 0; i < n; i++) {
			if (A[i] != i + 1)
				return i + 1;
		}
		return n + 1;
	}

}
