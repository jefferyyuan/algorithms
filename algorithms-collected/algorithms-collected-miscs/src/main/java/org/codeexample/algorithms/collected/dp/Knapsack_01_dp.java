package org.codeexample.algorithms.collected.dp;

/**
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/DynProg/Progs/
 * Knapsack/Knapsack_01_dp.java
 */
public class Knapsack_01_dp {
	static int M[][]; // M[i,j] = Max value achieved using

	// items 1..i in knapsack cap j

	/*
	 * ============================================================= make_M(v,
	 * w, M, n, C): make the M[] array
	 * 
	 * v[i] = value of each of item i w[i] = weight of item i M[i,j] = Max value
	 * achieved by packing items 1..i in knapsack of cap j
	 * 
	 * n = # items C = capacity
	 * 
	 * Return: the value M[n, C] (which is the max value you can pack in a
	 * 0,1-knapsack of capacity C
	 * =============================================================
	 */
	static int make_M(int[] v, int[] w, int[][] M, int n, int C) {
		int sol1, sol2;
		int i, j, k, max;
		for (i = 0; i <= n; i++)
			M[i][0] = 0; // 0 value for knapsack of capacity 0

		for (j = 0; j <= C; j++)
			M[0][j] = 0; // No items to pack !

		/*
		 * --------------------------------------------------- The other cases
		 * (starting from 1 to C = M.length-1)
		 * ---------------------------------------------------
		 */
		for (j = 1; j <= C; j++) {
			// j capacity, ith item
			for (i = 1; i <= n; i++) {

				/*
				 * =============================================================
				 * Compute: M[i, j] = Max value achieved by packing items 1..i
				 * in knapsack of cap j
				 * 
				 * When for any value j, we have the solutions for:
				 * 
				 * M[0,0] M[0,1] ... M[0,j-1] M[1,0] M[1,1] ... M[1,j-1] ....
				 * M[i-1,0] M[i-1,1] ... M[i-1,j-1]
				 * 
				 * We can compute M[i,j] as:
				 * 
				 * M[i,j] = max( (M[i-1, j-w[i]] + v[i]) , (M[i-1, j] + 0) )
				 * =============================================================
				 */

				if (C < 20)
					System.out.println("\nComputing M[" + i + "," + j + "]");

				/*
				 * -------------------------------------------- Check if we can
				 * use the i-th typed item
				 * 
				 * Note: the info. on the i-th item is found in v[i-1] and
				 * w[i-1] --------------------------------------------
				 */
				if (w[i - 1] > j) {
					/*
					 * ------------------------ i-th item does not fit..
					 * ------------------------
					 */

					M[i][j] = M[i - 1][j];
				} else {
					sol1 = M[i - 1][j - w[i - 1]] + v[i - 1]; // Find best
																// solution
					// with item k packed

					sol2 = M[i - 1][j]; // Find best solution
					// with item k left out

					/*
					 * --------------------------------------- Determine the
					 * best (last) item to use
					 * ---------------------------------------
					 */
					if (sol1 >= sol2)
						M[i][j] = sol1;
					else
						M[i][j] = sol2;
				}

				if (C < 20)
					System.out.println("=====> M[" + i + "," + j + "] = "
							+ M[i][j]);

			}

		}

		return (M[n][C]);
	}

	public static void main(String[] args) {
		int[] v = { 3, 4, 5, 6 };
		int[] w = { 2, 3, 4, 5 };

		int C, r;

		C = 5; // Around 53 it starts to slow...

		M = new int[v.length + 1][C + 1];

		r = make_M(v, w, M, v.length, C);

		/*
		 * for (i = 0; i <= C; i ++) System.out.println("M[" + i + "] = " +
		 * M[i]);
		 */

		System.out.println("Max value for knapsack of cap " + C + " = " + r);
	}

}
