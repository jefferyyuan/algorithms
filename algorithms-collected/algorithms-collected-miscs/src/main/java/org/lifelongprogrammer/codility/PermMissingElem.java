package org.lifelongprogrammer.codility;

import static org.junit.Assert.assertEquals;

public class PermMissingElem {

	public static void main(String[] args) {

		PermMissingElem instance = new PermMissingElem();

		int[] A = null;
		int ans = -1;

		A = new int[] { 2, 3, 1, 5 };
		ans = instance.solution1(A);
		System.out.println(ans);
		assertEquals(4, ans);

		A = new int[] {};
		ans = instance.solution1(A);
		System.out.println(ans);
		assertEquals(1, ans);

		A = new int[] { 1 };
		ans = instance.solution1(A);
		System.out.println(ans);
		assertEquals(2, ans);

		A = new int[] { 2 };
		ans = instance.solution1(A);
		System.out.println(ans);
		assertEquals(1, ans);

		A = new int[] { 1, 3 };
		ans = instance.solution1(A);
		System.out.println(ans);
		assertEquals(2, ans);

		A = new int[] { 1, 2 };
		ans = instance.solution1(A);
		System.out.println(ans);
		assertEquals(3, ans);

		A = new int[] { 2, 3 };
		ans = instance.solution1(A);
		System.out.println(ans);
		assertEquals(1, ans);
	}

	/**
	 * Use XOR
	 */
	public int solution1(int[] A) {
		if (A == null) {
			throw new NullPointerException("A can't be null.");
		}
		if (A.length == 0) {
			return 1;
		}

		int xor = A[0];
		for (int i = 1; i < A.length; i++) {
			xor ^= A[i];
		}

		for (int i = 1; i <= A.length + 1; i++) {
			xor ^= i;
		}

		return xor;
	}

	public int solution2(int[] A) {
		// write your code in Java SE 8
		if (A == null) {
			throw new NullPointerException("A can't be null.");
		}
		if (A.length == 0) {
			return 1;
		}

		for (int i = 0; i < A.length; i++) {
			int curr = A[i];
			if (curr < 0) {
				curr = -curr;
			}

			int index = curr - 1;
			if (index < A.length) {// common mistake: < or <=,< A.length or <<
									// A.length - 1
				A[index] = -A[index];
			}
			// else ignored intentionally
		}

		int missing = -1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] > 0) {
				// means i+1 missing
				missing = i + 1;
			}
			// else we can change its value to positive, but not required
		}

		if (missing == -1) {
			missing = A.length + 1;
		}
		return missing;
	}

}
