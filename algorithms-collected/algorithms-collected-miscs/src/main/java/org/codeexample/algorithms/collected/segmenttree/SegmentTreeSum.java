package org.codeexample.algorithms.collected.segmenttree;

import java.util.*;

public class SegmentTreeSum {
	private static int[] st;

	// Construction of Segment Tree
	public static int[] constructSegmentTree(int[] arr) {
		int height = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
		int size = 2 * (int) Math.pow(2, height) - 1;
		st = new int[size];
		constructST(arr, 0, arr.length - 1, 0);
		return st;
	}

	public static int constructST(int[] arr, int ss, int se, int si) {
		// base case
		if (ss == se) {
			st[si] = arr[ss];
			return st[si];
		}
		int mid = ss + (se - ss) / 2;
		st[si] = constructST(arr, ss, mid, si * 2 + 1)
				+ constructST(arr, mid + 1, se, si * 2 + 2);
		return st[si];
	}

	// Finding The sum of given Range
	public static int findRangeSum(int qs, int qe, int[] arr) {
		int n = arr.length;
		if (qs < 0 || qe > n - 1 || qs > qe) {
			throw new IllegalArgumentException("Invalid arguments");
		}
		return findSum(0, n - 1, qs, qe, 0);
	}

	/**
	 * @param ss
	 * @param se
	 * @param qs
	 * @param qe
	 * @param si
	 *            index --> Index of current node in the segment tree. Initially
	 *            0 is passed as root is always at index 0
	 * @return
	 */
	public static int findSum(int ss, int se, int qs, int qe, int si) {
		if (ss > qe || se < qs)
			return 0;
		if (qs <= ss && qe >= se)
			return st[si];

		int mid = ss + (se - ss) / 2;
		return findSum(ss, mid, qs, qe, si * 2 + 1)
				+ findSum(mid + 1, se, qs, qe, si * 2 + 2);
	}

	// Updating a particular index's value
	static void updateValue(int arr[], int i, int newVal) {
		if (i < 0 || i > arr.length - 1)
			throw new IllegalArgumentException();
		int difference = newVal - arr[i];
		arr[i] = newVal;
		updValue(arr, 0, arr.length - 1, i, difference, 0);
	}

	static void updValue(int[] arr, int ss, int se, int i, int difference,
			int si) {
		if (i < ss || i > se)
			return;
		st[si] = st[si] + difference;
		if (ss != se) {
			int mid = ss + (se - ss) / 2;
			updValue(arr, ss, mid, i, difference, si * 2 + 1);
			updValue(arr, mid + 1, se, i, difference, si * 2 + 2);
		}
	}

	public static void main(String[] args) throws Exception {
		int[] arr = { 1, 3, 5, 7, 9, 11 };
		System.out.println("Height = "
				+ (int) Math.ceil(Math.log(arr.length) / Math.log(2)));
		constructSegmentTree(arr);
		System.out.println("Values array = " + Arrays.toString(arr));
		System.out.println("Segement Tree array = " + Arrays.toString(st));
		System.out.println("Range sum from index 2 to index 4 = "
				+ findRangeSum(2, 4, arr));

		// Update value
		updateValue(arr, 2, 2);
		System.out
				.println("Updated value at index 2 in the original array to " + 2);
		System.out.println("Range sum from index 0 to index 2 = "
				+ findRangeSum(0, 2, arr));
	}
}