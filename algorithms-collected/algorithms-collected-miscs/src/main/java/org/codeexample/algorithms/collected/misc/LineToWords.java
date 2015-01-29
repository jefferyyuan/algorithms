package org.codeexample.algorithms.collected.misc;

import java.util.ArrayList;
import java.util.HashSet;

public class LineToWords {

	static String[] dictionary = { "i", "like", "lik", "care", "take",
			"takers", "caretakers", "eice", "ball", "iceball" };

	static HashSet<String> dictionarySet = new HashSet<String>();
	static String input = "ilikeiceball";

	public static void main(String args[]) {
		for (String word : dictionary)
			dictionarySet.add(word);

		ArrayList<String> words = new ArrayList<String>();
		boolean possible = linetoWordsDP(words, 0, 0);

		System.out.println("Line to words' breakup: "
				+ (possible ? "possible" : "not possible"));
		for (String word : words)
			System.out.print(word + ", ");
	}

	private static boolean linetoWordsDP(ArrayList<String> words, int startPos,
			int pos) {
		ArrayList<ArrayList<String>> lookup = getLookup();

		for (int i = 0; i <= input.length(); i++) {
			for (int j = i - 1; j >= 0; j--) {
				String word = input.substring(j, i);
				if (dictionarySet.contains(word) == false)
					continue;

				ArrayList<String> wordsAtIndexJ = lookup.get(j);
				ArrayList<String> wordsAtIndexI = lookup.get(i);

				if (wordsAtIndexJ.size() == 0) // no valid word list at index j
				{
					if (j != 0) // if j is 0, only then we can form a word from
								// j to i
						continue;
				} else {
					wordsAtIndexI.addAll(wordsAtIndexJ);
				}
				wordsAtIndexI.add(word);
				break;
			}
		}

		ArrayList<String> finalWords = lookup.get(lookup.size() - 1);
		boolean possible = finalWords.size() != 0;
		if (possible)
			words.addAll(finalWords);

		return possible;
	}

	private static ArrayList<ArrayList<String>> getLookup() {
		ArrayList<ArrayList<String>> lookup = new ArrayList<ArrayList<String>>(
				input.length() + 1);
		for (int i = 0; i <= input.length(); i++)
			lookup.add(new ArrayList<String>());

		return lookup;
	}
}
