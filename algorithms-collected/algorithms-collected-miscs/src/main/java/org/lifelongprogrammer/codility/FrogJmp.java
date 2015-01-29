package org.lifelongprogrammer.codility;

import static org.junit.Assert.assertEquals;

public class FrogJmp {

	public static void main(String[] args) {
		FrogJmp instance = new FrogJmp();
		int X, Y, D;
		int ans;

		X = 10;
		Y = 85;
		D = 30;

		ans = instance.solution(X, Y, D);
		System.out.println(ans);
		assertEquals(3, ans);

		X = 10;
		Y = 30;
		D = 30;
		ans = instance.solution(X, Y, D);
		System.out.println(ans);
		assertEquals(1, ans);

		X = 10;
		Y = 40;
		D = 30;
		ans = instance.solution(X, Y, D);
		System.out.println(ans);
		assertEquals(1, ans);
	}

	// o logn
	public int solution(int X, int Y, int D) {
		// write your code in Java SE 8

		int times = (Y - X) / D; // init value is 0 or 1;

		if ((Y - X) % D != 0) {
			++times;
		}
		// while (X < Y) {
		// X += D;
		// ++times;
		// }

		return times;
	}
}
