package org.codeexample.algorithms.collected.string.search;

/* Title:     Boyer-Moore string matching algorithm
 * Author:    H.W. Lang
 *            Fachhochschule Flensburg, University of Applied Sciences
 *            Flensburg, Germany
 * Date:      2007
 * Mail:      lang@fh-flensburg.de
 * Web:       http://www.inf.fh-flensburg.de/lang/algorithmen/pattern/bmen.htm
 * Reference: R.S. Boyer, J.S. Moore: A Fast String Searching Algorithm.
 *            Communications of the ACM, 20, 10, 762-772 (1977)
 */

public class BmStringMatcher {
	private char[] pattern, text; // pattern, text
	private int patLen, textLen; // pattern length, text length
	private static int alphabetsize = 256;
	private String matches; // string of match positions
	private char[] showmatches;// char array that shows matches

	private int[] occurrence; // occurence function
	private int[] f;
	private int[] suffix;
	private static String name = "Boyer-Moore";

	public BmStringMatcher() {
		occurrence = new int[alphabetsize];
	}

	public void search(String tt, String pp) {
		setText(tt);
		setPattern(pp);
		bmSearch();
	}

	/**
	 * sets the text
	 */
	private void setText(String tt) {
		textLen = tt.length();
		text = tt.toCharArray();
		initmatches();
	}

	/**
	 * sets the pattern
	 */
	public void setPattern(String pp) {
		patLen = pp.length();
		pattern = pp.toCharArray();
		f = new int[patLen + 1];
		suffix = new int[patLen + 1];
		bmPreprocess();
	}

	/**
	 * computation of the occurrence function
	 */
	private void bmInitocc() {
		char a;
		int j;

		for (a = 0; a < alphabetsize; a++)
			occurrence[a] = -1;

		for (j = 0; j < patLen; j++) {
			a = pattern[j];
			occurrence[a] = j;
		}
	}

	/**
	 * preprocessing according to the pattern (part 1)
	 */
	private void bmPreprocess1() {
		int i = patLen, j = patLen + 1;
		f[i] = j;
		while (i > 0) {
			while (j <= patLen && pattern[i - 1] != pattern[j - 1]) {
				if (suffix[j] == 0)
					suffix[j] = j - i;
				j = f[j];
			}
			i--;
			j--;
			f[i] = j;
		}
	}

	/**
	 * preprocessing according to the pattern (part 2)
	 */
	private void bmPreprocess2() {
		int i, j;
		j = f[0];
		for (i = 0; i <= patLen; i++) {
			if (suffix[i] == 0)
				suffix[i] = j;
			if (i == j)
				j = f[j];
		}
	}

	/**
	 * preprocessing according to the pattern
	 */
	private void bmPreprocess() {
		bmInitocc();
		bmPreprocess1();
		bmPreprocess2();
	}

	/**
	 * initializes match positions and the array showmatches
	 */
	private void initmatches() {
		matches = "";
		showmatches = new char[textLen];
		for (int i = 0; i < textLen; i++)
			showmatches[i] = ' ';
	}

	/**
	 * searches the text for all occurences of the pattern
	 */
	private void bmSearch() {
		int i = 0, j;
		while (i <= textLen - patLen) {
			j = patLen - 1;
			while (j >= 0 && pattern[j] == text[i + j])
				j--;
			if (j < 0) {
				report(i);
				i += suffix[0];
			} else
				i += Math.max(suffix[j + 1], j - occurrence[text[i + j]]);
		}
	}

	/**
	 * reports a match
	 */
	private void report(int i) {
		matches += i + " ";
		showmatches[i] = '^';
	}

	/**
	 * returns the match positions after the search
	 */
	public String getMatches() {
		return matches;
	}

	// only for test purposes
	public static void main(String[] args) {
		BmStringMatcher stm = new BmStringMatcher();
		System.out.println(name);
		String tt, pp;
		tt = "abcdabcd";
		pp = "abc";
		stm.search(tt, pp);
		System.out.println(pp);
		System.out.println(tt);
		System.out.println(stm.showmatches);
		System.out.println(stm.getMatches());
		tt = "abababaa";
		pp = "aba";
		stm.search(tt, pp);
		System.out.println(pp);
		System.out.println(tt);
		System.out.println(stm.showmatches);
		System.out.println(stm.getMatches());
	}

} // end class BmStringMatcher
