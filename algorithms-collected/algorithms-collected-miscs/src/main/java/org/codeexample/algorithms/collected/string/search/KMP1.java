package org.codeexample.algorithms.collected.string.search;

/* --------------------------------------------------------------
 KMP alg

 In this version of the KMP algorithm, I am using
 Goodrich's KMP failure function computation...

 S.Y. Cheung - 2/7/2011
 -------------------------------------------------------------- */

import java.util.*;

public class KMP1 {
	public static int[] KMP_failure_function(String P) {
		int k, x, m;
		int f[] = new int[P.length()];

		m = P.length();

		f[0] = 0;

		k = 1;
		x = 0;

		while (k < m) {
			if (P.charAt(k) == P.charAt(x)) {
				f[k] = x + 1; // Match rule...

				k++;
				x++;
			} else {
				if (x == 0) {
					f[k] = 0;
					k++;
				} else {
					x = f[x - 1];
				}
			}
		}

		return (f);
	}

	public static int KMP(String T, String P, int[] f) {
		int i0, i, j, m, n;

		n = T.length();
		m = P.length();

		i0 = 0;
		i = 0;
		j = 0;

		while (i < n) {
			printState(T, P, i, j); // Show the current state

			if (P.charAt(j) == T.charAt(i)) {
				i++;
				j++;

				if (j == m) // Check if pattern is complete
					return (i0); // FOUND !!

			} else {
				System.out.println();
				System.out.println("**** Mismatch");
				if (j == 0) {
					System.out
							.println("-- First character, slide P 1 position");

					i0++; // Slide 1 character over
					i = i0;
					j = 0;
				} else {
					System.out.println("-- FAST slide !!!");

					int k = f[j - 1];

					j = k; // Start j at character after matching prefix
					i0 = (i - j); // Slide (i-j) characters further
					// i is unchanged
				}
				System.out.println("====================");
			}
		}

		return (-1);
	}

	public static void main(String[] args) {
		int i, r;
		String T, P;
		Scanner in;
		int[] f;

		in = new Scanner(System.in);

		System.out.println("Try");
		System.out.println("T = baaababbaaabaabaabaaaaa");
		System.out.println("P = baabaaa");
		System.out.println();

		System.out.print("T = ");
		T = in.nextLine();

		System.out.print("P = ");
		P = in.nextLine();

		f = KMP_failure_function(P);

		for (i = 0; i < P.length(); i++) {
			System.out.println("f(" + i + ") = " + f[i]);
		}
		System.out.println();
		System.out.println("Matching....");
		System.out.println();

		r = KMP(T, P, f);

		if (r == -1)
			System.out.println("No match found...\n");
		else {
			System.out.println("Match found at position " + r);
			System.out.println();
			System.out.println("0123456789012345678901234567890123456789");
			System.out.println(T);

			for (i = 0; i < r; i++)
				System.out.print(" ");
			System.out.println(P);
			System.out.println();
		}
	}

	/*
	 * ===================================================== Variables and
	 * Methods to make the algorithm visual
	 * =====================================================
	 */
	public static String T_ruler, P_ruler;

	public static String ruler(int n) {
		String out = "";
		char x = '0';

		for (int i = 0; i < n; i++) {
			out = out + x;
			x++;
			if (x > '9')
				x = '0';
		}

		return out;
	}

	public static void printState(String T, String P, int i, int j) {
		if (T_ruler == null)
			T_ruler = ruler(T.length());

		if (P_ruler == null)
			P_ruler = ruler(P.length());

		System.out.println("=====================================");
		System.out.println("Matching: i = " + i + ", j = " + j);

		System.out.println("   " + T_ruler);
		System.out.println("   " + T);
		System.out.print("   ");
		for (int k = 0; k < i - j; k++)
			System.out.print(" ");
		System.out.println(P);

		System.out.print("   ");
		for (int k = 0; k < i - j; k++)
			System.out.print(" ");
		System.out.println(P_ruler);

		System.out.print("   ");
		for (int k = 0; k < i; k++)
			System.out.print(" ");
		System.out.println("^");

		System.out.print("   ");
		for (int k = 0; k < i; k++)
			System.out.print(" ");
		System.out.println("|");
		System.out.println();
	}

}
