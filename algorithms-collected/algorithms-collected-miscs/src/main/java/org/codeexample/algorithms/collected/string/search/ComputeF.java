package org.codeexample.algorithms.collected.string.search;
/* ----------------------------------
   My own KMP Failure function alg

     S.Y. Cheung - 3/3/2013
   ---------------------------------- */

import java.util.*;

public class ComputeF
{
   public static int[] KMP_failure_function(String P)
   {
      int k, i, x, m;
      int f[] = new int[P.length()];

      String s;
   
      m = P.length();
   
      f[0] = 0;

      for ( k = 1; k < m; k++ )
      {
         // Compute f[k]

         s = P.substring(0,k+1);
         System.out.println("-----------------------------------------------");
         System.out.println("Prefix = " + s + " --- Computing f("+k+"):");

         i = k-1;           // First try to use f(k-1) to compute f(k)
         x = f[i];
   
         System.out.println("===================================");
         System.out.println("Try using: f(" + i + ") = " + x );
         printState(s, s, k, x);

         while ( P.charAt(x) != P.charAt(k) )
         {
	    i = f[i]-1;     // Try the next candidate f(.) to compute f(k)

	    if ( i < 0 )    // Search ended in failure....
	       break;

            x = f[i];

            System.out.println("===================================");
            System.out.println("Try using: f(" + i + ") = " + x );
            printState(s, s, k, x);
         }

         if ( i < 0 )
         {
            System.out.println("No overlap possible... --> f["+k+"] = 0");
            f[k] = 0;          // No overlap possible
         }
         else
         {
            f[k] = f[i] + 1;   // Compute f(k) using f(i)

            System.out.println("Overlap found ... --> f["+k+"] = "+f[k]);
         }
      }

      return(f);
   }


   public static void main(String[] args)
   {
      String P;
      Scanner in;
      int[] f;


      in = new Scanner( System.in );

      System.out.print("P = ");
      P = in.nextLine();
      System.out.println();

      f = KMP_failure_function(P);

      for (int i = 0; i < P.length(); i++ )
      {
         System.out.println("f("+i+") = " + f[i]);
      }

      System.out.println();
   }



   /* =====================================================
      Variables and Methods to make the algorithm visual
      ===================================================== */
   public static String T_ruler, P_ruler;

   public static String ruler(int n)
   {
      String out = "";
      char   x = '0';

      for ( int i = 0; i < n; i++ )
      {
         out = out + x;
	 x++;
	 if ( x > '9' )
	    x = '0';
      }

      return out;
   }

   public static void printState(String T, String P, int i, int j)
   {
      T_ruler = ruler( T.length() );

      P_ruler = ruler( P.length() );

      System.out.println("=====================================");
      System.out.println("Matching: i = " + i + ", j = " + j);

      System.out.println("   " + T_ruler );
      System.out.println("   " + T);
      System.out.print("   ");
      for ( int k = 0; k < i-j; k++)
         System.out.print(" ");
      System.out.println(P);

      System.out.print("   ");
      for ( int k = 0; k < i-j; k++)
         System.out.print(" ");
      System.out.println( P_ruler );

      System.out.print("   ");
      for ( int k = 0; k < i; k++)
         System.out.print(" ");
      System.out.println("^");

      System.out.print("   ");
      for ( int k = 0; k < i; k++)
         System.out.print(" ");
      System.out.println("|");
      System.out.println();
   }




}

