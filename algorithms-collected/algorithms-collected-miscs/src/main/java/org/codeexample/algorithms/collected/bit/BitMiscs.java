package org.codeexample.algorithms.collected.bit;

public class BitMiscs {

	public static void main(String[] args) {
		System.out.println(nextPowerOf2(5));
		System.out.println(add(-1, -1));
		// System.out.println(power(3, -2));
		// System.out.println(addOne1(5));
		// System.out.println(addOne2(5));
		// System.out.println(decrement(5));

	}

	public static int nextPowerOf2(int n) {
		int p = 1;
		if (n != 0 && ((n & (n - 1)) == 0))
			return n;
		while (p < n) {
			p <<= 1;
		}
		return p;
	}

	public static int add(int x, int y) {
		// Iterate till there is no carry
		while (y != 0) {
			// carry now contains common set bits of x and y
			int carry = x & y;

			// Sum of bits of x and y where at least one of the bits is not set
			x = x ^ y;

			// Carry is shifted by one so that adding it to x gives the required
			// sum
			y = carry << 1;
		}
		return x;
	}

	public static int bitConvertBetweenTwoTintegers(int x, int y) {
		// XOR x and y to get the bit difference between x and y
		int z = x ^ y;
		return numberofBits(z);
	}

	public static int numberofBits(int x) {
		int n = 0;
		while (x != 0) {
			n = n + 1;
			// clear the least significant bit set
			x = x & (x - 1);
		}
		return n;
	}

	public static int findNumberOfBits(int x, int y) {
		int bitCount = 0;

		int z = x ^ y; // XOR x and y

		while (z != 0) {
			// increment count if last binary digit is a 1:
			bitCount += z & 1;
			z = z >> 1; // shift Z by 1 bit to the right
		}

		return bitCount;
	}

	public static float power(float x, int y) {
		float temp;
		if (y == 0)
			return 1;
		temp = power(x, y / 2);
		if (y % 2 == 0)
			return temp * temp;
		else {
			if (y > 0)
				return x * temp * temp;
			else
				return (temp * temp) / x;
		}
	}

	public static int addOne1(int x) {
		int m = 1;
		/* Flip all the set bits until we find a 0 */
		while ((x & m) > 0) {
			x = x ^ m;
			m <<= 1;
		}
		/* flip the rightmost 0 bit */
		x = x ^ m;
		return x;
	}

	public static int addOne2(int x) {
		return (-(~x));
	}

	public static int decrement(int x) {
		//
		return (~(-x));
	}

}
