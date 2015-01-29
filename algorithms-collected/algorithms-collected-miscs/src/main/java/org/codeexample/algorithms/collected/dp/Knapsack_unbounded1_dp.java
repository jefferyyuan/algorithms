package org.codeexample.algorithms.collected.dp;

public class Knapsack_unbounded1_dp
{

    /* ========================================================
       M_dp(W) = max value achieved by knapsack with capacity W
       ======================================================== */
    static int M_dp(int[] v, int[] w, int W)
    {
       int[] sol, mySol;
       int i, myFinalSol;

       int[] M;                 // Data structure to store results
       int   C;                 // Index

       sol   = new int[v.length];
       mySol = new int[v.length];

       M = new int[W + 1];      // Create array

       /* ---------------------------
	  Base cases
	  --------------------------- */
       M[0] = 0;

       /* ==============================================
          The other values M[C]
	  ============================================== */
       for ( C = 1; C <= W; C++ )
       {
          /* ---------------------------------------
             Solve the appropriate smaller problems
	     --------------------------------------- */
          for ( i = 0; i < v.length; i++ )
          {
             if ( C >= w[i] )
                sol[i] = M[ C-w[i] ]; // Knapsack capacity reduced by w[i]
                                      // because it has item i packed in 
				      // it already   
             else
	        sol[i] = 0;        // Not enough space to  pack item i
          }

          /* ---------------------------------------------
             Use the solutions to the smaller problems
	     to solve original problem
	     --------------------------------------------- */
          for ( i = 0; i < v.length; i++ )
          {
             if ( C >= w[i] )
	        mySol[i] = sol[i] + v[i];   // Value is increased by v[i]
                                            // because it has item i packed in 
				            // it already
             else
                mySol[i] = 0;        // Not enough space to  pack item i
          }


          /* *************************
             Find the best (maximum)
	     ************************* */
          M[C] = mySol[0];
          for ( i = 1; i < v.length; i++ )
             if ( mySol[i] > M[C] )
	         M[C] = mySol[i];
       }
       return M[W]; 	// Return best value for knapsack of cap = W
   }


   public static void main(String[] args)
   {
       int[] v = {1, 2, 3, 5, 6, 8};
       int[] w = {2, 3, 5, 8, 9, 13};

       int C, r;

       C = 18;                  // Around 53 it starts to slow...
       r = M_dp(v, w, C);

       System.out.println("Max value packed in backpack of capacity " 
				+ C + " = " + r);
   }

}
