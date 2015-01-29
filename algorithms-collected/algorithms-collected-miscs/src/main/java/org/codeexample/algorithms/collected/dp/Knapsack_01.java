package org.codeexample.algorithms.collected.dp;

import java.util.Scanner;

public class Knapsack_01 {

	/*
	 * =========================================================== M(k, C) = max
	 * value achieved by knapsack with capacity C filled with items 0, 1, ...,
	 * k-1 ===========================================================
	 */
	static int M(int[] v, int[] w, int k, int C) {
		int sol1, sol2, mySol1, mySol2, myFinalSol;

		/*
		 * --------------------------- Base cases ---------------------------
		 */
		if (k == 0) {
			return (0); // No more items to pick from
		}

		if (C == 0) {
			return (0); // No more space
		}

		/*
		 * -------------------------------------------- Check if we can use the
		 * n-th typed item --------------------------------------------
		 */
		if (w[k - 1] > C) // Item k-1 does not fit...
		{
			myFinalSol = M(v, w, k - 1, C); // We must leave item k-1 out
		} else {
			/*
			 * ==================================== Solve smaller problems
			 * ====================================
			 */
			sol1 = M(v, w, k - 1, C); // Try leaving item k out
			sol2 = M(v, w, k - 1, C - w[k - 1]); // Try packing item k

			/*
			 * ========================================================== Use
			 * solution of smaller problems to solve my problem
			 * ==========================================================
			 */
			mySol1 = sol1; // I did not pack item k-1, so no added value
			mySol2 = sol2 + v[k - 1]; // I packed item k-1, so add v[k-1] !

			if (mySol1 > mySol2)
				myFinalSol = mySol1;
			else
				myFinalSol = mySol2;
		}

		return (myFinalSol); // Return my solution
	}

	public static void main(String[] args) {
		int[] v, w;
		int n;
		int C, r;

		Scanner in = new Scanner(System.in);

		System.out.print("Number of items: ");
		n = in.nextInt();
		System.out.println();

		v = new int[n];
		w = new int[n];

		for (int i = 0; i < n; i++) {
			System.out.print("Weight of item " + i + ": ");
			w[i] = in.nextInt();
			System.out.print("Value of item  " + i + ": ");
			v[i] = in.nextInt();
			System.out.println();
		}

		System.out.print("Capacity of knapsack: ");
		C = in.nextInt();

		r = M(v, w, w.length, C);

		System.out.println("\nMax value packed in backpack = " + r);
	}

}
