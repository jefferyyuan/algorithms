package org.codeexample.algorithms.collected.misc;

import java.util.ArrayList;
import java.util.List;

public class TwoDisjointSubsequencesWithMaximumDifference {

	public static void main(String[] args) {
		for (int j = 1; j <= 10; j++) {
			int arr4[] = new int[10];
			for (int i = 0; i < 10; i++)
				arr4[i] = (Math.random() > 0.5 ? 1 : -1)
						* ((int) (10 * Math.random()));
			disjointSubsequencesWithMaximumDifference(arr4);
		}
	}

	static void disjointSubsequencesWithMaximumDifference(int[] arr) {
		List<Integer> finalMax = null;
		List<Integer> finalMin = null;
		Integer maxDiff = null;

		for (int i = 0; i < arr.length; i++) // foreach array index
		{
			// compute max and min subsequence from 0 to i
			List<Integer> endsMax1 = maxKadane(arr, 0, i);
			List<Integer> endsMin1 = minKadane(arr, 0, i);

			// compute max and min subsequence from i to end
			List<Integer> endsMax2 = maxKadane(arr, i, arr.length);
			List<Integer> endsMin2 = minKadane(arr, i, arr.length);

			// In these 4 subsequences, choose difference among max-min pairs of
			// subsequences
			// right max subseq with left min subseq and
			// right min subseq with left max subseq
			int diff1 = sum(arr, endsMax1) - sum(arr, endsMin2);
			int diff2 = sum(arr, endsMax2) - sum(arr, endsMin1);

			// Choose the pair which has bigger difference
			// And replace the finalMax ones with that if its the biggest till
			// now
			if (diff1 > diff2) {
				if (maxDiff == null || diff1 > maxDiff) {
					maxDiff = diff1;
					finalMax = endsMax1;
					finalMin = endsMin2;
				}
			} else {
				if (maxDiff == null || diff2 > maxDiff) {
					maxDiff = diff2;
					finalMax = endsMax2;
					finalMin = endsMin1;
				}
			}
		}

		// print the final output
		printArr(arr, "Array      : ", 0, arr.length - 1);
		printArr(arr, "Max subseq : ", finalMax.get(0), finalMax.get(1));
		printArr(arr, "Min subseq : ", finalMin.get(0), finalMin.get(1));
		System.out.println();
	}

	/************** Function to find maximum subsequence ******************/
	static List<Integer> maxKadane(int[] arr, int start, int end) {
		int maxSum = arr[start];
		int currSum = 0;

		// variables to store the start and end of the maximum and current
		// subsequence
		int currEnd1 = start;
		int currEnd2 = -1;
		int maxEnd1 = start;
		int maxEnd2 = start;

		int maxIndex = start; // stores the index of maximum element

		for (int i = start; i < end; i++) {
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

			if (arr[maxIndex] < arr[i])
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
	static List<Integer> minKadane(int[] arr, int start, int end) {
		int minSum = arr[start];
		int currSum = 0;

		// variables to store the start and end of the minimum and current
		// subsequence
		int currEnd1 = start;
		int currEnd2 = -1;
		int minEnd1 = start;
		int minEnd2 = start;

		int minIndex = start; // stores the index of minimum element

		for (int i = start; i < end; i++) {
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

			if (arr[minIndex] > arr[i])
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

	// Helper function to compute array sum from index 'start' to 'end'
	static int sum(int[] arr, List<Integer> ends) {
		int sum = 0;
		for (int i = ends.get(0); i <= ends.get(1); i++)
			sum += arr[i];
		return sum;
	}

	// Helper function to print an array with sum from index 'start' to 'end'
	static void printArr(int arr[], String msg, int start, int end) {
		System.out.print("\n" + msg + "(" + start + "," + end + ") : ");
		int sum = 0;
		for (int i = start; i <= end; i++) {
			System.out.print(arr[i] + ",");
			sum += arr[i];
		}
		System.out.print(" = " + sum);
	}
}
