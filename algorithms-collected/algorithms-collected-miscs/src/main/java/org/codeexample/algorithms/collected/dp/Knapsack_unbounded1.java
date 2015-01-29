package org.codeexample.algorithms.collected.dp;

//http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/DynProg/Progs/Knapsack/Knapsack_unbounded1.java
public class Knapsack_unbounded1 {

	/*
	 * ======================================================== M(C) = max value
	 * achieved by knapsack with capacity C
	 * ========================================================
	 */
	static int M(int[] v, int[] w, int C) {
		int[] sol, mySol;
		int i, myFinalSol;

		sol = new int[v.length];
		mySol = new int[v.length];

		/*
		 * --------------------------- Base cases ---------------------------
		 */
		if (C == 0) {
			return (0);
		}

		/*
		 * ============================================== Divide and conquer
		 * procedure ==============================================
		 */

		/*
		 * --------------------------------------- Solve the appropriate smaller
		 * problems ---------------------------------------
		 */
		for (i = 0; i < v.length; i++) {
			if (C >= w[i])
				sol[i] = M(v, w, C - w[i]); // Knapsack capacity reduced by w[i]
											// because it has item i packed in
			// it already
			else
				sol[i] = 0; // Not enough space to pack item i
		}

		/*
		 * --------------------------------------------- Use the solutions to
		 * the smaller problems to solve original problem
		 * ---------------------------------------------
		 */
		for (i = 0; i < v.length; i++) {
			if (C >= w[i])
				mySol[i] = sol[i] + v[i]; // Value is increased by v[i]
											// because it has item i packed in
			// it already
			else
				mySol[i] = 0; // Not enough space to pack item i
		}

		/* *************************
		 * Find the best (maximum)************************
		 */
		myFinalSol = mySol[0];
		for (i = 1; i < v.length; i++)
			if (mySol[i] > myFinalSol)
				myFinalSol = mySol[i];

		return myFinalSol; // Return the overal best solution
	}

	public static void main(String[] args) {
		int[] v = { 1, 2, 3, 5, 6, 8 };
		int[] w = { 2, 3, 5, 8, 9, 13 };

		int C, r;

		C = 18; // Around 53 it starts to slow...
		r = M(v, w, C);

		System.out.println("Max value packed in backpack of capacity " + C
				+ " = " + r);
	}

}
