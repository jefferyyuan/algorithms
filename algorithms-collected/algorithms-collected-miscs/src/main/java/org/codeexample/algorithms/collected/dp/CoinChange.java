package org.codeexample.algorithms.collected.dp;

public class CoinChange {
	static int findM(int Am, int v[])
    {
       int[] M;
       int[] sol, mySol;
       int j, k, min;

       M = new int[Am+1];              // Store results

       sol = new int[v.length];
       mySol = new int[v.length];

       /* ---------------------------
          Base case
          --------------------------- */
       M[0] = 0;        // 0 coins needed to make change for $0

       /* ---------------------------------------------------
          The other cases (starting from 1 to M.length - 1)

	  Follow direction of data flow !
          --------------------------------------------------- */
       for ( j = 1; j <= Am; j++ )
       {
          /* ============================================
	     Find min # coin to make change for $j
	     ============================================ */

          for ( k = 0; k < v.length; k++ )
             mySol[k] = -1;                   // Initialize mySol[]

          /* --------------------------------------------------------
             Try every denomination k = 1,2,..,C for the last coin
             -------------------------------------------------------- */
          for ( k = 0; k < v.length; k++ )
          {
             /* --------------------------------------------
                Check if we can use the k-th denomination
                -------------------------------------------- */
             if ( v[k] <= j )
             {
                /* ------------------------
                   Divide step
                   ------------------------ */
                sol[k] = M[j - v[k]];     // Use coin v[k] as last coin
                mySol[k] = sol[k] + 1;    // Solution to make change for $j      
             }
          }

          /* --------------------------------------------------------
             Find the minimum of all mySol[...]
             -------------------------------------------------------- */
          M[j] = -1;

          for ( k = 0; k < v.length; k++ )
          {
             if ( mySol[k] > 0 )
             {
                if ( M[j] == -1 || mySol[k] < M[j] )
                   M[j] = mySol[k];
             }
          }
       }

       return( M[Am] );    // Min # coins to change $Am
   }
	
	static int M(int j, int[] v) {
		int[] sol, mySol;
		int myFinalSol;
		int k;

		sol = new int[v.length];
		mySol = new int[v.length];

		/*
		 * --------------------------- Base cases ---------------------------
		 */
		if (j == 0) {
			return (0);
		}

		/*
		 * ================================================== Initialize mySol[]
		 * ==================================================
		 */
		for (k = 0; k < v.length; k++)
			mySol[k] = -1; // -1 means: no solution

		/*
		 * -------------------------------------------------------- Try every
		 * denomination k = 1,2,..,C for the last coin
		 * --------------------------------------------------------
		 */
		for (k = 0; k < v.length; k++) {
			/*
			 * -------------------------------------------- Check if we can use
			 * the k-th denomination
			 * --------------------------------------------
			 */
			if (v[k] <= j) {
				/*
				 * ------------------------ Divide step ------------------------
				 */
				sol[k] = M(j - v[k], v); // Use coin v[k] as last coin

				mySol[k] = sol[k] + 1; // Solution to make change for $j
			}
		}

		/*
		 * -------------------------------------------------------- Find the
		 * minimum for ALL mySol[...] values
		 * 
		 * Note: -1 means do NOT use !
		 * --------------------------------------------------------
		 */
		myFinalSol = -1;

		for (k = 0; k < v.length; k++) {
			if (mySol[k] >= 0 /* Don't use -1 values */) {
				if (myFinalSol == -1 || mySol[k] < myFinalSol)
					myFinalSol = mySol[k];
			}
		}

		return (myFinalSol); // Return best solution
	}
}
