package org.codeexample.algorithms.collected.number.miscs;


public class SingleNumber {
	public static void main(String[] args) {

		System.out.println(singleNumber(new int[] { 12, 12, 1, 12 }));
	}

	public static int singleNumber(int A[]) {
		int n = A.length;
		int ones = 0, twos = 0, xthrees = 0;
		for (int i = 0; i < n; ++i) {
			twos |= (ones & A[i]);
			ones ^= A[i];
			xthrees = ~(ones & twos);
			ones &= xthrees;
			twos &= xthrees;
		}

		return ones;
	}
}
