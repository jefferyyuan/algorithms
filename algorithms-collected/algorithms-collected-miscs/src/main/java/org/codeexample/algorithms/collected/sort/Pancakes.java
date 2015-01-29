package org.codeexample.algorithms.collected.sort;

/*     This is a program to perform "pancake sorting" (or
 *  sorting by prefix reversal).  The idea is that you have
 *  a stack of pancakes of varying (but integer) diameter.
 *  You want to "sort" the pancakes, i.e. arrange them in
 *  a stack from smallest to largest, with largest on 
 *  the bottom of the stack.  
 *
 *  The only operation that you're permitted to do is
 *  to pick up and flip an entire stack of pancakes.  
 *
 *  Input will be read from standard input, and can consist
 *  of one of more lines of input.  Each line of input represents
 *  a separate stack of pancakes.  It is assumed that the stack
 *  is entered from top-to-bottom order, i.e., the first one
 *  in the list is the one on top of the stack.  Also, we
 *  refer to the pancake on top of the stack as pancake #1,
 *  and the one on the bottom as pancake #n (where n=number
 *  in the stack).   
 *
 *  Output should first consist of echoing the stack of pancakes,
 *  and then should consist of a sequence of numbers, where
 *  an integer i means that the stack including  pancake i 
 *  (so pancakes i, i-1, i-2, ..., 1) are flipped over.  
 *  A "0" indicates that no more flips are necessary.    
 *
 *  Input should be in the following format:
 *    n_1 n_2 .....  n_k
 *    n_1 n_2 .....  n_j
 *    ....
 *    ....
 *
 *  Each list consists of k integers (k can vary from stack to stacki, 
 *  but we're going to assume always that k <=300).
 *  
 *  Note:  Integers in each list need *not* be distinct, and this is
 *  something that you should keep in mind.
 *
 *  It's easiest if you redirect input from a file (ask me if you
 *  don't know how to do this).  For example you can try this command:
 *     % java Pancakes <inputfile
 *
 *  RAM   15 February 2009
 */
import java.io.*;
import java.util.*;

class Pancakes {
	public static void main(String args[]) // entry point from OS
	{
		Scanner s, ls;
		int pancakes[] = new int[300];
		int k;

		s = new Scanner(System.in); // create new input Scanner

		while (s.hasNextLine()) /* While there's more data to process... */
		{
			/* Read the integers */
			ls = new Scanner(s.nextLine());
			k = 0;
			while (ls.hasNextInt())
				pancakes[k++] = ls.nextInt();

			processStack(pancakes, k);
		}

	} /* end of "main" procedure */

	public static void processStack(int pancakes[], int L) {
		int bottom, max_so_far, index, i, j, k;

		/* Print out the stack again */
		for (j = 0; j < L; j++)
			System.out.print(pancakes[j] + " ");
		System.out.println();

		System.out.print("Flip sequence: ");

		for (bottom = L - 1; bottom > 0; bottom--) {

			/*
			 * First, find a maximum of the substack of elements pancakes[0],
			 * pancakes[bottom] Note that if this is duplicated, I am finding
			 * the one closest to the bottom of the substack.
			 */
			index = bottom;
			max_so_far = pancakes[bottom];
			for (i = bottom - 1; i >= 0; i--)
				if (pancakes[i] > max_so_far) {
					max_so_far = pancakes[i];
					index = i;
				}

			/* Now, if we have to flip anything... */
			if (index != bottom) {
				if (index != 0) /*
								 * if we have to flip something other than the
								 * one on top
								 */
					System.out.print((index + 1) + " ");
				/* then we flip this one */
				System.out.print((bottom + 1) + " ");

				/*
				 * Now construct the new stack. Note that I'm not explicitly
				 * performing the flip(s) that would be performed, but am
				 * directly constructing it by copying portions of the stack of
				 * pancakes.
				 * 
				 * If you carefully study what I'm doing here, you should be
				 * able to follow along and see that it's correct, i.e., that I
				 * end up with the stack that would be present after performing
				 * the one or two flips to bring the next largest pancake into
				 * position.
				 */
				int temp[] = new int[L];
				j = 0;
				for (i = bottom; i > index; i--, j++)
					temp[j] = pancakes[i];
				for (i = 0; i < index; i++, j++)
					temp[j] = pancakes[i];
				temp[j++] = pancakes[index];

				/*
				 * Having constructed the new (top) portion, insert it back into
				 * the array for the next time through the loop.
				 */
				for (i = 0; i <= bottom; i++)
					pancakes[i] = temp[i];

			} /* end of performing the next flip(s), if necessary */

		} /* done processing the whole stack */

		/* We're done, so print out a "0" */
		System.out.println("0");
		System.out.println();

		return;

	} /* end of "processStack" procedure */

} /* end of "Pancakes" program */
