package org.codeexample.algorithms.collected.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Interview Street - String Similarity
 * 
 * For two strings A and B, we define the similarity of the strings to be the
 * length of the longest prefix common to both strings. For example, the
 * similarity of strings "abc" and "abd" is 2, while the similarity of strings
 * "aaa" and "aaab" is 3. Calculate the sum of similarities of a string S with
 * each of it's suffixes.
 *
 * @author raju rama krishna
 * @see http://javatroops.blogspot.co.nz
 *
 */
public class StringSimilarity {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int i = 0; i < T; i++) {
			char[] s = in.readLine().toCharArray();
			System.out.println(getcount(s));
		}
	}

	private static int getcount(char[] s) {
		int i = 0;
		int n = s.length;
		int total = 0;
		while (i < n) {
			int sum = 0;
			int k = 0;
			while (i + k < n) {
				if (s[k] == s[i + k]) {
					sum++;
				} else {
					break;
				}
				k++;
			}
			total += sum;
			i++;
		}
		return total;
	}
}