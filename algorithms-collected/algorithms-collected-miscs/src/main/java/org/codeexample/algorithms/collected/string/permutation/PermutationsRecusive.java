package org.codeexample.algorithms.collected.string.permutation;

// CSE 143, Winter 2011, Marty Stepp                                   
// This program uses recursive backtracking to print all permutations                                        
// (all possible arrangements of letters) of a given string.                                               


/**
 * Source:
 * https://www.cs.washington.edu/education/courses/143/11wi/lectures/02-14
 * /programs/Permutations.java
 * 
 */
public class PermutationsRecusive {
	public static void main(String[] args) {
		permute("MARTY");
		System.out.println();
		permute("JANE");
	}

	// Prints all possible permutations of letters of the given string,
	// one per line. For example, permute("MARTY") prints MARTY, MATRY,
	// ATRMY, YARMT, etc. Uses backtracking to generate permutations.
	// Precondition: s is not null (throws NullPointerException if so)
	public static void permute(String s) {
		permute(s, "");
	}

	// s: set of letters available to choose
	// chosen: set of letters I already chose
	// s = "MARTY", chosen = ""
	// s = "ARTY" , chosen = "M"
	// s = "ARY" , chosen = "MT"
	// ....
	// s = "" , chosen = "MTRYA"

	// Recursive helper method for choosing letters and backtracking.
	// Additional 'chosen' parameter stores the letters we have picked so far.
	private static void permute(String s, String chosen) {
		// System.out.println("permute " + s + ", " + chosen);
		if (s.length() == 0) {
			System.out.println(chosen); // base case
		} else {
			for (int i = 0; i < s.length(); i++) {
				// choose
				String chosenLetter = s.substring(i, i + 1);
				s = s.substring(0, i) + s.substring(i + 1, s.length()); // remove
																																// firstLetter
																																// from s
				chosen += chosenLetter;

				// explore
				permute(s, chosen);

				// un-choose
				chosen = chosen.substring(0, chosen.length() - 1);
				s = s.substring(0, i) + chosenLetter + s.substring(i);
			}
		}
	}

	// an alternative implementation that takes advantage of the fact that
	// strings are immutable to make the "choosing" and "un-choosing" easier
	/*
	 * private static void permute(String s, String chosen) {
	 * Recursion.println("permute " + s + ", " + chosen); if (s.length() == 0) {
	 * System.out.println(chosen); // base case } else { for (int i = 0; i <
	 * s.length(); i++) { // choose String chosenLetter = s.substring(i, i+1);
	 * String newS = s.substring(0, i) + s.substring(i+1, s.length()); // remove
	 * firstLetter from s String newChosen = chosen + chosenLetter;
	 * 
	 * // explore permute(newS, newChosen);
	 * 
	 * // un-choose // nothing to do! } } }
	 */
}