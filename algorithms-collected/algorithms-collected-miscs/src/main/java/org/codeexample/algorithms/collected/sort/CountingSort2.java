package org.codeexample.algorithms.collected.sort;

import java.util.Collections;

import com.google.common.primitives.Ints;

public class CountingSort2 {
	public static void main(String[] args) {
		CountingSort2 countingSort = new CountingSort2();
		int[] arrayToSort = new int[] { 0, 3, 1, 0, 5, 2, 4, 5, 2, 0 };
		int[] sortedArray = countingSort.counting_sort(arrayToSort);

		for (int i = 0; i < sortedArray.length; i++) {
			System.out.print(sortedArray[i] + ",");
		}

	}

	public int[] counting_sort(int[] arrayToSort) {
		int maxValue = Collections.max(Ints.asList(arrayToSort));
		// getMaxVal(arrayToSort);
		int[] finalSortedArray = new int[arrayToSort.length];
		int[] tempArray = new int[maxValue + 1];

		for (int i = 0; i < arrayToSort.length; i++) {
			tempArray[arrayToSort[i]] = tempArray[arrayToSort[i]] + 1;
		}

		for (int i = 1; i < maxValue + 1; i++) {
			tempArray[i] = tempArray[i] + tempArray[i - 1];
		}

		for (int i = 0; i < arrayToSort.length; i++) {
			finalSortedArray[tempArray[arrayToSort[i]] - 1] = arrayToSort[i];
			tempArray[arrayToSort[i]] = tempArray[arrayToSort[i]] - 1;
		}
		return finalSortedArray;
	}

	private int getMaxVal(int[] arrayToSort) {
		int maxVal = Integer.MIN_VALUE;
		for (int i : arrayToSort) {
			if (i > maxVal) {
				maxVal = i;
			}
		}
		return maxVal;
	}
}