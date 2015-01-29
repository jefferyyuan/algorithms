package org.codeexample.algorithms.collected.misc;

import java.util.ArrayList;
import java.util.HashSet;

public class LineToWordsCur {

	static String[] dictionary = { "i", "like", "care", "take", "takers",
			"caretakers", "ice", "ball", "iceball" };

	static HashSet<String> dictionarySet = new HashSet<String>();
	static String input = "ilikeiceball";

	public static void main(String args[]) {
		for (String word : dictionary)
			dictionarySet.add(word);

		ArrayList<String> words = new ArrayList<String>();
		boolean possible = linetoWords(words, 0, 0);
		System.out.println(possible);
	}

	private static boolean linetoWords(ArrayList<String> words, int startPos,
			int pos) {
		if (startPos >= input.length())
			return true;

		while (pos <= input.length()) {
			String word = input.substring(startPos, pos);
			System.out.println(startPos + " to " + pos + ", word=" + word);

			if (dictionarySet.contains(word)) {
				words.add(word);
				if (linetoWords(words, pos, pos))
					return true;
				words.remove(words.size() - 1);
			}
			pos++;
		}
		return false;
	}
}