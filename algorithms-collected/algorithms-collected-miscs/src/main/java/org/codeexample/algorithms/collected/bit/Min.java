package org.codeexample.algorithms.collected.bit;

public class Min {

	public static void main(String[] args) {
		int x = Integer.MIN_VALUE;
//		System.out.println(x & 1);
//		System.out.println(x & -1);
		int y = 10;
		System.out.println(min(x,y));
		System.out.println(max(x, y));
	}

	public static int min(int x, int y) {
		int diff = (x - y);
		System.out.println(diff + ":" + (diff >> 31));
		return y + (diff & diff >> 31);
	}

	public static int max(int x, int y) {
		int diff = (x - y);
		return x - (diff & diff >> 31);
	}
}
