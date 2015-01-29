package org.lifelongprogrammer.codility;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TapeEquilibrium {

	public static void main(String[] args) {
		TapeEquilibrium instance = new TapeEquilibrium();

		Map<int[], Integer> inAns = new HashMap<int[], Integer>();
		// the input and answer from the origin question
		inAns.put(new int[] { 3, 1, 2, 4, 3 }, 1);

		inAns.put(new int[] { 1 }, 1);
		inAns.put(new int[] { 1, 3 }, 2);
		inAns.put(new int[] { 3, 1 }, 2);
		inAns.put(new int[] { -1 }, 1);
		inAns.put(new int[] { 1, -1 }, -1);

		for (Entry<int[], Integer> entry : inAns.entrySet()) {
			assertEquals(instance.solution(entry.getKey()), entry.getValue()
					.intValue());
		}

		int[] A;
		int ans;

		A = new int[] { 1, -1 };
		ans = instance.solution(A);
		System.out.println(ans);
		assertEquals(2, ans);

		A = new int[] { 3, 1, 2, 4, 3 };
		ans = instance.solution(A);
		assertEquals(1, ans);

		A = new int[] { 1 };
		ans = instance.solution(A);
		System.out.println(ans);
		assertEquals(1, ans);

		A = new int[] { 3, 1 };
		ans = instance.solution(A);
		System.out.println(ans);
		assertEquals(2, ans);

		A = new int[] { 1, 3 };
		ans = instance.solution(A);
		System.out.println(ans);
		assertEquals(2, ans);

		A = new int[] { 1, 2, 3 };
		ans = instance.solution(A);
		System.out.println(ans);
		assertEquals(0, ans);

		A = new int[] { 3, 2, 1 };
		ans = instance.solution(A);
		System.out.println(ans);
		assertEquals(0, ans);

		A = new int[] { -1 };
		ans = instance.solution(A);
		System.out.println(ans);
		assertEquals(1, ans);
	}

	/**
	 * O(1) space
	 */
	public int solution(int[] A) {
		if (A == null)
			throw new IllegalArgumentException("Can't be null");
		if (A.length == 0) {
			return 0;
		}
		if (A.length == 1) {
			return Math.abs(A[0]);
		}

		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
		}
		int minRst = Integer.MAX_VALUE;

		// ignore last one, as second array can't be empty
		int prefixSum = 0;
		for (int i = 0; i < A.length - 1; i++) {
			prefixSum += A[i];
			int diff = Math.abs(2 * prefixSum - sum);
			if (diff < minRst) {
				minRst = diff;
			}
		}
		return minRst;
	}

	/**
	 * O(n) space
	 */
	public int solution1(int[] A) {
		if (A == null)
			throw new IllegalArgumentException("Can't be null");
		if (A.length == 0) {
			return 0;
		}
		if (A.length == 1) {
			return Math.abs(A[0]);
		}

		int[] prefixSum = new int[A.length];

		prefixSum[0] = A[0];
		for (int i = 1; i < A.length; i++) {
			prefixSum[i] += prefixSum[i - 1] + A[i];
		}
		int sum = prefixSum[A.length - 1];
		int minRst = Integer.MAX_VALUE;

		// ignore last one, as second array can't be empty
		for (int i = 0; i < A.length - 1; i++) {
			int diff = Math.abs(2 * prefixSum[i] - sum);
			if (diff < minRst) {
				minRst = diff;
			}
		}

		return minRst;
	}
}
