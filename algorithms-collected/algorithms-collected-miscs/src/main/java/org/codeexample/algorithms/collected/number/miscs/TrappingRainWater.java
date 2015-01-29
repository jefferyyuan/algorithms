package org.codeexample.algorithms.collected.number.miscs;

import java.util.Arrays;

public class TrappingRainWater {

	public static void main(String[] args) {

		int[] a = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(trap2(a));
	}

	public static int trap2(int A[]) {
		// Start typing your C/C++ solution below
		// DO NOT write int main() function
		int n = A.length;
		if (n <= 2)
			return 0;
		int sum = A[0], mi = 0;
		for (int i = 1; i < n; i++) {
			sum += A[i];
			if (A[i] > A[mi])
				mi = i;
		}
		int left = 0, right = 0, min = 0;
		for (int i = 1; i <= mi; i++)
			if (A[i] >= A[min]) {
				left += A[min] * (i - min);
				min = i;
			}
		min = n - 1;
		for (int j = n - 2; j >= mi; j--)
			if (A[j] >= A[min]) {
				right += A[min] * (min - j);
				min = j;
			}
		return left + right + A[mi] - sum;
	}

	// 388ms for 315 test cases
	public static int trap(int[] A) {
		if (A == null || A.length == 0)
			return 0;
		int n = A.length;
		// Used to record the maximum height to the left and the right of each
		// bar
		int[] maxHeights = new int[n];
		// Scan from left to right, find the maximum height to the left of each
		// bar
		int maxHeight = 0;
		for (int i = 0; i < n; i++) {
			maxHeights[i] = maxHeight;
			maxHeight = Math.max(maxHeight, A[i]);
		}
		System.out.println(Arrays.toString(maxHeights));
		// Scan from right to left, find the maximum height to the right of each
		// bar
		// The minimum of them is the height of the water (if any) on the bar
		// The difference between the height of the water and that of the bar is
		// the
		// volume of the water on that bar
		maxHeight = 0;
		int volume = 0; // Accumulated volume of water
		for (int i = n - 1; i >= 0; i--) {
			maxHeights[i] = Math.min(maxHeight, maxHeights[i]);
			maxHeight = Math.max(maxHeight, A[i]);
			if (maxHeights[i] > A[i]) // Has water on the bar
				volume += maxHeights[i] - A[i]; // Accumulate the volume of the
												// water
		}
		System.out.println(Arrays.toString(maxHeights));

		return volume;
	}
}