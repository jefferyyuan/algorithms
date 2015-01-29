package org.codeexample.algorithms.collected.misc;

import java.util.ArrayList;
import java.util.List;

public class TwoSubsequencesWithMaximumDifference {

	// generate random input from the main function
	public static void main(String[] args) {
		for (int j = 1; j <= 10; j++) {
			int arr4[] = new int[10];
			for (int i = 0; i < 10; i++)
				arr4[i] = ((i % j == 0) ? (-1) : 1)
						* ((int) (10 * Math.random()));
			printSubsequencesWithMaximumDifference(arr4);
		}
	}

	// major function to find the maximum difference between two subsequences
	static void printSubsequencesWithMaximumDifference(int[] arr) {
		List<Integer> endsMax = maxKadane(arr);
		List<Integer> endsMin = minKadane(arr);

		// print the input and the output
		printArr(arr, "Array      : ", 0, arr.length - 1);
		printArr(arr, "Max subseq : ", endsMax.get(0), endsMax.get(1));
		printArr(arr, "Min subseq : ", endsMin.get(0), endsMin.get(1));
		System.out.println();
	}

	/************** Function to find maximum subsequence ******************/
	static List<Integer> maxKadane(int[] arr) {
		int maxSum = arr[0];
		int currSum = 0;

		// variables to store the start and end of the maximum and current
		// subsequence
		int currEnd1 = 0;
		int currEnd2 = -1;
		int maxEnd1 = 0;
		int maxEnd2 = 0;

		int maxIndex = 0; // stores the index of maximum element

		for (int i = 0; i < arr.length; i++) {
			currSum += arr[i];
			if (currSum >= maxSum) {
				currEnd2 = i;
				// Update all variables associated with maximum.
				maxSum = currSum;
				maxEnd1 = currEnd1;
				maxEnd2 = currEnd2;
			}

			if (currSum <= 0) {
				currSum = 0;
				currEnd1 = i + 1;
			}

			if (arr[maxIndex] < arr[i]) // find index of the maximum element
				maxIndex = i;
		}

		List<Integer> lst = new ArrayList<Integer>();
		if (maxSum < 0) {
			lst.add(maxIndex);
			lst.add(maxIndex);
		} else {
			lst.add(maxEnd1);
			lst.add(maxEnd2);
		}
		return lst;
	}

	/************** Function to find minimum subsequence ******************/
	static List<Integer> minKadane(int[] arr) {
		int minSum = arr[0];
		int currSum = 0;

		// variables to store the start and end of the minimum and current
		// subsequence
		int currEnd1 = 0;
		int currEnd2 = -1;
		int minEnd1 = 0;
		int minEnd2 = 0;

		int minIndex = 0; // stores the index of minimum element

		for (int i = 0; i < arr.length; i++) {
			currSum += arr[i];
			if (currSum <= minSum) {
				currEnd2 = i;
				// Update all variables associated with minimum.
				minSum = currSum;
				minEnd1 = currEnd1;
				minEnd2 = currEnd2;
			}

			if (currSum >= 0) {
				currSum = 0;
				currEnd1 = i + 1;
			}

			if (arr[minIndex] > arr[i]) // find index of the minimum element
				minIndex = i;
		}

		List<Integer> lst = new ArrayList<Integer>();
		if (minSum > 0) {
			lst.add(minIndex);
			lst.add(minIndex);
		} else {
			lst.add(minEnd1);
			lst.add(minEnd2);
		}
		return lst;
	}

	// helper function to print the array
	static void printArr(int arr[], String msg, int start, int end) {
		System.out.print("\n" + msg);
		int sum = 0;
		for (int i = start; i <= end; i++) {
			System.out.print(arr[i] + ",");
			sum += arr[i];
		}
		System.out.print(" = " + sum);

	}
}