package org.codeexample.algorithms.collected.number.miscs;

public class KthSmallestInTwoSortedArrays {
	/**
	 * Problem : Find the kth smallest from two sorted arrays (length m and n)
	 * combined Solution : Find the kth smallest from among first k elements of
	 * each array. We will use the binary search here.
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] A = { 4, 5, 7, 45, 67, 77, 86, 94, 101 };
		int[] B = { 1, 3, 6, 8, 23, 24, 34, 44, 51 };
		int k = 6;
		getKthSmallestInTwoArrays(A, B, k);
	}

	private static void getKthSmallestInTwoArrays(int[] a, int[] b, int k) {
		int lowA = 0, lowB = 0, highA = k - 1, highB = k - 1;
		if (a.length < (k - 1)) {
			highA = a.length - 1;
		}
		if (b.length < (k - 1)) {
			highB = b.length - 1;
		}
		if ((highA + highB) < k) {
			// insufficient elements
			return;
		}
		int midA = 0, midB = 0;
		int result = 0;
		while (k >= 0) {
			midA = lowA + (highA - lowA) / 2;
			midB = lowB + (highB - lowB) / 2;
			if (a[midA] >= b[midB]) {
				// it means the first midA elements of A are all greater than
				// first midB elements of B
				// it means that the kth smallest lies in second half of B OR
				// first half of A
				k = k - (midB - lowB + 1);
				result = b[midB];
				highA = midA - 1;
				lowB = midB + 1;
			} else if (a[midA] < b[midB]) {
				// it means the first midB elements of B are all greater than
				// first midA elements of A
				// it means that the kth smallest lies in second half of A OR
				// first half of B
				k = k - (midA - lowA + 1);
				result = a[midA];
				highB = midB - 1;
				lowA = midA + 1;
			}
			if (k == 0)
				break;
		}
		System.out.println(result);
	}
}