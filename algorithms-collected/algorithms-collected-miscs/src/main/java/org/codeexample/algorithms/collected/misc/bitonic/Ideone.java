package org.codeexample.algorithms.collected.misc.bitonic;

//package gfg;
//http://www.geeksforgeeks.org/maximum-length-bitonic-subarray/
class Ideone {

	static int Bitonic(int a[]) {
		boolean xset = false, yset = false;

		int x = 0, y = 0;
		int i;

		for (i = 1; i < a.length; i++) {
			if (a[i] > a[i - 1] && !xset) {
				x = i - 1;
				xset = true;
				yset = false;
			}
			if (xset && a[i] < a[i - 1]) {
				y = i;
				yset = true;
			}
		}
		if (!yset)
			y = i - 1;
		if (!xset) {
			x = 0;
			y = a.length - 1;
		}

		for (int j = x; j <= y; j++)
			System.out.print(a[j] + " ");

		return y - x + 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ar[] = { 1, 2, 3, 4, 2, 10, 11, 12, 13, 14, 15, 16, 15 };
		int ar2[] = { 12, 4, 78, 90, 45, 23 };
		int ar3[] = { 10 };
		int ar4[] = { 10, 20, 30, 40 };
		int ar5[] = { 40, 30, 20, 10 };

		int x = Bitonic(ar);
		System.out.println("| length: " + x);

		x = Bitonic(ar2);
		System.out.println("| length: " + x);

		x = Bitonic(ar3);
		System.out.println("| length: " + x);

		x = Bitonic(ar4);
		System.out.println("| length: " + x);

		x = Bitonic(ar5);
		System.out.println("| length: " + x);

	}

}
