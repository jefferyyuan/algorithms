package org.codeexample.algorithms.collected.string.substringsearch;

import java.io.IOException;

public class KMP {
	private int[] lps;
	private String pat;

	public KMP(String pat) {
		this.pat = pat;
		lps = new int[pat.length()];
		computLPSArray(pat);
	}

	private void computLPSArray(String pat) {
		int M = pat.length();
		int i = 1;
		int len = 0; // lenght of the previous longest prefix suffix

		while (i < M) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else // (pat[i] != pat[len])
			{
				if (len != 0) {
					// This is tricky. Consider the example AAACAAAA and i = 7.
					len = lps[len - 1];
					// Also, note that we do not increment i here
				} else // if (len == 0)
				{
					lps[i] = 0;
					i++;
				}
			}
		}
	}

	/** Function to find match for a pattern **/
	public int search(String text) {
		int i = 0, j = 0;
		int lens = text.length();
		int lenp = pat.length();
		while (i < lens && j < lenp) {
			if (text.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
			} else if (j != 0) {
				j = lps[j - 1] + 1;
			} else {
				i++;
			}
		}
		return ((j == lenp) ? (i - lenp) : -1);
	}

	/** Main Function **/
	public static void main(String[] args) throws IOException {
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in));
		// System.out.println("Knuth Morris Pratt Test\n");
		// System.out.println("\nEnter Text\n");
		// String text = br.readLine();
		// System.out.println("\nEnter Pattern\n");
		// String pattern = br.readLine();
		KMP kmp = new KMP("AABAACAABAA");
		System.out.println(kmp.search("aAABAACAABAAa"));
	}
}