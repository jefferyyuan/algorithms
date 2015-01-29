package org.codeexample.algorithms.collected.misc;

public class StringInterleavingRecur {

	public static void main(String args[]) {
		String s1 = "Hello";
		String s2 = "Wo";
		String s = "HWelolo";
		System.out.println(isInterleaved1(s1, s2, s, 0, 0, 0));
	}

	boolean isInterleaved(String A, String B, String C) {
		int M = A.length();
		int N = B.length();

		if ((M + N) != C.length())
			return false;

		// lookup[i][j] is true if C[0..i+j-1] is an interleaving of A[0..i-1]
		// and B[0..j-1].
		boolean lookup[][] = new boolean[M + 1][N + 1];

		for (int i = 0; i <= M; i++) {
			for (int j = 0; j <= N; j++) {
				// If both A and B are empty, then C must also be empty (due to
				// length matching)
				// And since one empty string is interleaving of other two empty
				// strings, lookup[0][0] is true.
				if (i == 0 && j == 0)
					lookup[i][j] = true;

				// If A is empty, check one-to-one match with B
				else if (i == 0 && B.charAt(j-1) == C.charAt(j-1))
					lookup[i][j] = lookup[i][j - 1];

				// If B is empty, check one-to-one match with A
				else if (j == 0 && A.charAt(i-1) == C.charAt(i-1))
					lookup[i][j] = lookup[i - 1][j];

				// Regular check for interleaving

				if (A[i - 1] == C[i + j - 1] && B[j - 1] == C[i + j - 1])
					lookup[i][j] = (lookup[i - 1][j] || lookup[i][j - 1]);

				else if (A[i - 1] == C[i + j - 1])
					lookup[i][j] = lookup[i - 1][j];

				else if (B[j - 1] == C[i + j - 1])
					lookup[i][j] = lookup[i][j - 1];

				// Its too soon to return a false if C[i+j-1] matches neither A
				// or B.
				// Think why?
			}
		}

		return lookup[M][N];
	}

	private static boolean isInterleaved1(String s1, String s2, String s,
			int pos1, int pos2, int pos) {

		System.out.println(s1.substring(pos1) + "," + s2.substring(pos2) + ","
				+ s.substring(pos));

		if (pos >= s.length())
			return true;

		boolean match1 = false;
		boolean match2 = false;

		if (pos1 < s1.length())
			match1 = (s.charAt(pos) == s1.charAt(pos1));

		if (pos2 < s2.length())
			match2 = (s.charAt(pos) == s2.charAt(pos2));

		return ((match1 && isInterleaved1(s1, s2, s, pos1 + 1, pos2, pos + 1)) || (match2 && isInterleaved1(
				s1, s2, s, pos1, pos2 + 1, pos + 1)));

	}

}