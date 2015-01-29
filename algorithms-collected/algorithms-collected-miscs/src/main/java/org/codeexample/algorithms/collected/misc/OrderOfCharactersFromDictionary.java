package org.codeexample.algorithms.collected.misc;

import java.util.*;
import java.util.Map.Entry;

public class OrderOfCharactersFromDictionary {

	public static void main(String[] args) {
		int numChars = 7;

		Set<String> dict = generateRandomDictionary(numChars);
		int maxWordLen = findMaxWordLength(dict);

		HashMap<Character, Set<Character>> graph = buildGraph(numChars,
				maxWordLen, dict);
		printGraph(graph);

		LinkedHashSet<Character> result = new LinkedHashSet<Character>();
		findOrder(graph, result, numChars);

		System.out.print("\nResult: ");
		for (Character c : result)
			System.out.print(c + ", ");
	}

	static HashMap<Character, Set<Character>> buildGraph(int numChars,
			int maxWordLen, Set<String> dict) {
		HashMap<Character, Set<Character>> grph = new HashMap<Character, Set<Character>>();

		for (int i = 0; i < maxWordLen; i++) {
			Iterator<String> itr = dict.iterator();
			String word1 = itr.next();

			while (itr.hasNext()) {
				String word2 = itr.next();
				if (word1.length() > i && word2.length() > i) {
					if (word1.substring(0, i).equals(word2.substring(0, i)))
					{
						char c1 = word1.charAt(i);
						char c2 = word2.charAt(i);
						if (c1 != c2) {
							Set<Character> children1 = addOrGetChar(grph, c1);
//							Set<Character> children2 = addOrGetChar(grph, c1);
							children1.add(c2);
						}
					}
				}
				word1 = word2;
			}
		}

		return grph;
	}

	// Major function to traverse the built graph of characters and find the
	// existence of a path-length=numChars
	static void findOrder(HashMap<Character, Set<Character>> graph,
			LinkedHashSet<Character> result, int numChars) {
		Iterator<Entry<Character, Set<Character>>> itr = graph.entrySet()
				.iterator();
		while (itr.hasNext()) {
			Entry<Character, Set<Character>> ele = itr.next();

			result.add(ele.getKey());
			findOrder(ele.getKey(), result, graph, numChars);
			if (result.size() == numChars)
				return;
			result.clear();
		}
	}

	// Auxiliary function used with upper function
	static void findOrder(Character key, LinkedHashSet<Character> result,
			HashMap<Character, Set<Character>> graph, int numChars) {

		if (graph.containsKey(key) == false)
			return;

		for (Character c : graph.get(key)) {
			if (result.contains(c))
				return;
			result.add(c);
			findOrder(c, result, graph, numChars);
			if (result.size() == numChars)
				return;
			result.remove(c);
		}
	}

	// Helper function to check existence of key.
	// Adds the key if it is not present in the map.
	static Set<Character> addOrGetChar(HashMap<Character, Set<Character>> grph,
			char c) {
		Set<Character> children = grph.get(c);
		if (children == null) {
			children = new HashSet<Character>();
			grph.put(c, children);
		}
		return children;
	}

	// Helper function to find the maximum word length
	static int findMaxWordLength(Set<String> dict) {
		int maxWordLen = 0;
		for (String word : dict)
			if (word.length() > maxWordLen)
				maxWordLen = word.length();
		return maxWordLen;
	}

	// Helper function to create a random list of words
	static Set<String> generateRandomDictionary(int numChars) {
		Set<String> dictionary = new HashSet<String>();
		int charsPresent[] = new int[numChars];
		int charsProcessed = 0;
		int size = dictionary.size();

		while (charsProcessed < numChars || size < 2 * numChars) {
			int rndm = ((int) (Math.random() * 50)) % numChars;
			char c = (char) ('a' + rndm);

			if (charsPresent[rndm] == 0)
				charsProcessed++;
			charsPresent[rndm]++;

			if (Math.random() < 0.3) {
				dictionary.add("" + c);
			} else {
				Set<String> dictionary2 = new HashSet<String>();
				for (String w : dictionary) {
					if (Math.random() > 0.5 && w.length() < 6)
						dictionary2.add(w + c);
					else
						dictionary2.add(w);
				}
				dictionary = dictionary2;
			}
			size = dictionary.size();
		}

		Set<String> set = new TreeSet<String>();
		for (String w : dictionary)
			set.add(w);
		for (String w : set)
			System.out.println(w);

		return set;
	}

	// Helper function to print the graph of characters
	static void printGraph(HashMap<Character, Set<Character>> graph) {
		System.out.println();
		Iterator<Entry<Character, Set<Character>>> itr = graph.entrySet()
				.iterator();
		while (itr.hasNext()) {
			Entry<Character, Set<Character>> ele = itr.next();

			System.out.print("\n" + ele.getKey() + " = ");
			for (Character c : ele.getValue())
				System.out.print(c + ", ");
		}
		System.out.println();
	}

}