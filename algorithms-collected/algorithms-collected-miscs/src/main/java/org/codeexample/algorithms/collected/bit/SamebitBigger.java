package org.codeexample.algorithms.collected.bit;

public class SamebitBigger {

	public static void main(String[] args) {
		int x = 156;
		System.out.println(snoob(x));
		System.out.println(snoob(0));

	}

	public static int snoob(int x) {

		int rightOne;
		int nextHigherOneBit;
		int rightOnesPattern;

		int next = 0;

		if (x != 0) {

			// right most set bit
			rightOne = x & -x;

			// reset the pattern and set next higher bit
			// left part of x will be here
			nextHigherOneBit = x + rightOne;

			// nextHigherOneBit is now part [D] of the above explanation.

			// isolate the pattern
			rightOnesPattern = x ^ nextHigherOneBit;
			rightOnesPattern >>= 2;

			// right adjust pattern
			rightOnesPattern = (rightOnesPattern) / rightOne;

			// correction factor
			// rightOnesPattern >>= 2;

			// rightOnesPattern is now part [A] of the above explanation.

			// integrate new pattern (Add [D] and [A])
			next = nextHigherOneBit | rightOnesPattern;
		}

		return next;
	}
}
