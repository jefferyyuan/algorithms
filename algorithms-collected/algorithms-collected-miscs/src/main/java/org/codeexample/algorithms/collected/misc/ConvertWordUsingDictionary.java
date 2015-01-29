package org.codeexample.algorithms.collected.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

public class ConvertWordUsingDictionary {

	static String dictionary[] = { "cat", "rat", "hat", "sag", "bag", "bug",
			"dog", "hog", "hot", "dot", "rot", "lot", "log", "cry", "pry",
			"fry", "fat", "fog", "pot", "pie" };

	// Map whose key is (word - a character) + index-of-omitted-character
	// and whose value is list of words having the same key
	static HashMap<String, ArrayList<String>> smallerWordsMap = new HashMap<String, ArrayList<String>>();

	// Map to store minimum path from word 'k' to final word 'b'
	static HashMap<String, ArrayList<String>> wordToPathMap = new HashMap<String, ArrayList<String>>();

	// Set to mark the words already visited by the recursive function
	static HashSet<String> visitedWords = new HashSet<String>();

	// function to test the algorithm.
	public static void main(String[] args) {
		String a = "cat";
		String b = "dog";

		createSmallerWordMap();
		ArrayList<String> path = convertWord(a, b);
		printPath(a, path);
	}

	// For every word, takes out one character, forms a word with the remaining
	// characters
	// and forms a key by concatenating this smaller word with index of smaller
	// character.
	static void createSmallerWordMap() {
		for (String word : dictionary) {
			for (int i = 0; i < word.length(); i++) {
				// form a smaller word by omitting a character
				// index of omitted character is appended to the end of this
				// smaller word.
				String smallerWord = word.substring(0, i)
						+ word.substring(i + 1, word.length()) + i;

				// Use this smallerWord as a key in the hash-map
				// Value for this key is the list of words from which
				// this smaller word was formed.
				// Example: 'log' and 'leg' would both form a key 'lg1' when
				// char at index 1 is omitted
				ArrayList<String> list = smallerWordsMap.get(smallerWord);
				if (list == null) {
					list = new ArrayList<String>();
					smallerWordsMap.put(smallerWord, list);
				}
				list.add(word);
			}
		}
		printSmallerWordMap();
	}

	// Main function which uses the smaller-Word-Map created above to find
	// the shortest path from a to b
	static ArrayList<String> convertWord(String a, String b) {
		// If a path has been found already, return that
		ArrayList<String> existingPath = wordToPathMap.get(a);
		if (existingPath != null && existingPath.size() > 0)
			return existingPath;

		// Do not visit same word again.
		// This is used when recursion forms a cycle for example from
		// rat->cat->rat->cat...
		// The wordToPathMap does not help here because there is yet no path for
		// these words.
		if (visitedWords.contains(a))
			return null;
		visitedWords.add(a);

		System.out.println("Checking " + a + "->" + b);

		ArrayList<String> minPath = new ArrayList<String>();

		for (int i = 0; i < a.length(); i++) {
			String smallerWord = a.substring(0, i)
					+ a.substring(i + 1, a.length()) + i;

			ArrayList<String> list = smallerWordsMap.get(smallerWord);
			for (String word : list) {
				if (word.equals(a))
					continue;

				if (word.equals(b)) // match found
				{
					ArrayList<String> currPath = new ArrayList<String>();
					currPath.add(word);
					wordToPathMap.put(word, currPath);
					return currPath;
				}

				ArrayList<String> fwdPath = convertWord(word, b); // recursive
																	// search

				if (fwdPath != null && fwdPath.size() > 0) {
					if (minPath.size() == 0
							|| minPath.size() > 1 + fwdPath.size()) {
						minPath.clear();
						minPath.add(word);
						minPath.addAll(fwdPath);
					}
				}
			}
		}

		wordToPathMap.put(a, minPath);
		return minPath;
	}

	/****************** Debug Utilities *********************/
	static void printPath(String a, ArrayList<String> path) {
		System.out.println();
		if (path == null || path.size() == 0) {
			System.out.println("No path exists");
			return;
		}
		System.out.print(a);
		for (String w : path)
			System.out.print("->" + w);
	}

	static void printSmallerWordMap() {
		Iterator<Entry<String, ArrayList<String>>> itr = smallerWordsMap
				.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, ArrayList<String>> e = itr.next();
			String smallerWord = e.getKey();
			ArrayList<String> list = e.getValue();
			System.out.print(smallerWord + ": ");
			for (String word : list)
				System.out.print(word + ",");
			System.out.println();
		}
		System.out.println();
	}

}