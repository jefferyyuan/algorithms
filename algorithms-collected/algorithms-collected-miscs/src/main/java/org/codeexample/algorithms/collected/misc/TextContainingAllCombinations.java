package org.codeexample.algorithms.collected.misc;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class TextContainingAllCombinations {
	private static String generateDeBruijnSequence(String s,
			Set<String> combinations) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);

		// Start with all chars set to the shortest one
		String res = "";
		for (int i = 0; i < s.length(); i++)
			res += chars[0];
		combinations.add(res);

		// Now check all possible combinations
		int suffixLen = s.length() - 1;
		while (true) {
			String suffix = res.substring(res.length() - suffixLen);
			int i;
			// for (i=0; i<chars.length; i++)
			for (i = chars.length - 1; i >= 0; i--) {
				if (!combinations.contains(suffix + chars[i])) {
					res = res + chars[i];
					combinations.add(suffix + chars[i]);
					break;
				}
			}

			// if (i>=chars.length)
			if (i < 0)
				break;
		}
		return res;
	}

	public static void main(String args[]) {
		String s = "ABC";
		Set<String> combinations = new LinkedHashSet<String>();
		String res = generateDeBruijnSequence(s, combinations);
		System.out.println("Result: " + res);
		System.out.println("Result length: " + res.length());

		System.out.println("Combinations: ");
		for (String sub : combinations) {
			System.out.println(sub);
		}
		System.out.println("No of combinations: " + combinations.size());
	}
}