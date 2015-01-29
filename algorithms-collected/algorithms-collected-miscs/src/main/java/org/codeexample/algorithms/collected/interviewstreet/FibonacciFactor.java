package org.codeexample.algorithms.collected.interviewstreet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class FibonacciFactor {

	public static int T;
	public static int K[];

	public static LinkedList<Long> ll;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ll = new LinkedList<Long>();

		fib();

		T = in.nextInt();

		K = new int[T];

		Iterator ii = ll.iterator();

		for (int i = 0; i < T; i++) {
			K[i] = in.nextInt();
		}

		for (int i = 0; i < T; i++) {
			for (int j = 2; j <= K[i]; j++) {
				if (K[i] % j == 0) {
					ii.next();
					ii.next();
					while (ii.hasNext()) {
						Long tmp = (Long) ii.next();
						if (tmp % j == 0) {
							System.out.println(tmp + " " + j);
							j = K[i] + 1;
							ii = ll.iterator();
							break;
						}
					}
				}
			}
		}

	}

	private static long fib() {
		long x1 = 0;
		long x2 = 1;
		long tmp;
		long max = (long) Math.pow(10, 18);
		while (true) {
			if (x1 >= max) {
				break;
			}
			x1 = x1 + x2;
			tmp = x2;
			x2 = x1;
			x1 = tmp;
			ll.add(x1);
		}
		return x1;
	}

}