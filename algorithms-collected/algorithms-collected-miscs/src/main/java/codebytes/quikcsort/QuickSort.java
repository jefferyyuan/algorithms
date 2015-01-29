package codebytes.quikcsort;

import java.util.Arrays;

public class QuickSort {

	public static void quickSort(int array[]) {
		quickSorter(array, 0, array.length - 1);
	}

	public static void quickSorter(int array[], int lo, int hi) {
		if (lo > hi) {
			return;
		}

		// If array size is less than 5, we will use Insertion Sort
		if (hi - lo <= 5) {
			InsertionSort.insertionSort(array, lo, hi);
			return;
		}

		int m = median(array, lo, (lo + hi) / 2, hi);
		swap(array, m, hi);

		int partition = partition(array, lo, hi);
		quickSorter(array, lo, partition - 1);
		quickSorter(array, partition + 1, hi);
	}

	private static int partition(int array[], int lo, int hi) {
		int partitionIndex = lo;

		for (int i = lo; i < hi; ++i) {
			if (array[i] < array[hi]) {
				swap(array, partitionIndex, i);
				partitionIndex++;
			}
		}
		swap(array, partitionIndex, hi);
		return partitionIndex;
	}

	public static int median(int[] x, int a, int b, int c) {
		if (x[a] > x[b] && x[a] > x[c]) {
			if (x[b] > x[c])
				return b;
			else
				return c;
		} else if (x[b] > x[a] && x[b] > x[c]) {
			if (x[a] > x[c])
				return a;
			else
				return c;
		} else if (x[c] > x[a] && x[c] > x[b]) {
			if (x[a] > x[b])
				return a;
		}
		return b;
	}

	public static void swap(int array[], int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	public static void main(String[] args) {
		int[] array = new int[] { 3, 4, 3, 2, 1, 3, 44, 21, 3, 2, 33, 12, 123 };
		quickSort(array);
		System.out.println(Arrays.toString(array));
	}
}