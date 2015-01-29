package org.codeexample.algorithms.collected.string.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;
import java.math.BigInteger;

public class RabinKarp {
	/** String Pattern **/
	private String pat;
	/** pattern hash value **/
	private long patHash;
	/** pattern length **/
	private int PAT_LEN;
	/** Large prime **/
	private long PRIME;
	/** radix **/
	private int RADIX;
	/** R^(M-1) % Q **/
	private long RM;

	/** Constructor **/
	public RabinKarp(String txt, String pat) {
		this.pat = pat;
		RADIX = 256;
		PAT_LEN = pat.length();
		PRIME = longRandomPrime();
		/** precompute R^(M-1) % Q for use in removing leading digit **/
		RM = 1;
		for (int i = 1; i <= PAT_LEN - 1; i++)
			RM = (RADIX * RM) % PRIME;
		patHash = hash(pat, PAT_LEN);
		int pos = search(txt);
		if (pos == -1)
			System.out.println("\nNo Match\n");
		else
			System.out.println("Pattern found at position : " + pos);
	}

	/** Compute hash **/
	private long hash(String key, int M) {
		long h = 0;
		for (int j = 0; j < M; j++)
			h = (RADIX * h + key.charAt(j)) % PRIME;
		return h;
	}

	/** Funtion check **/
	private boolean check(String txt, int i) {
		for (int j = 0; j < PAT_LEN; j++)
			if (pat.charAt(j) != txt.charAt(i + j))
				return false;
		return true;
	}

	/** Funtion to check for exact match **/
	private int search(String txt) {
		int N = txt.length();
		if (N < PAT_LEN)
			return N;
		long txtHash = hash(txt, PAT_LEN);
		/** check for match at start **/
		if ((patHash == txtHash) && check(txt, 0))
			return 0;
		/** check for hash match. if hash match then check for exact match **/
		for (int i = PAT_LEN; i < N; i++) {
			// Remove leading digit, add trailing digit, check for match.
			txtHash = (txtHash + PRIME - RM * txt.charAt(i - PAT_LEN) % PRIME)
					% PRIME;
			txtHash = (txtHash * RADIX + txt.charAt(i)) % PRIME;

			// t = (d*(t - txt[i]*h) + txt[i+M])%q;
			//
			// // We might get negative value of t, converting it to positive
			// if(t < 0)
			// t = (t + q);
			// match
			int offset = i - PAT_LEN + 1;
			if ((patHash == txtHash) && check(txt, offset))
				return offset;
		}
		/** no match **/
		return -1;
	}

	/** generate a random 31 bit prime **/
	private static long longRandomPrime() {
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}

	/** Main Function **/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Rabin Karp Algorithm Test\n");
		System.out.println("\nEnter Text\n");
		String text = "AABA";// br.readLine();
		System.out.println("\nEnter Pattern\n");
		String pattern = "AB";// br.readLine();
		System.out.println("\nResults : \n");
		RabinKarp rk = new RabinKarp(text, pattern);
	}
}