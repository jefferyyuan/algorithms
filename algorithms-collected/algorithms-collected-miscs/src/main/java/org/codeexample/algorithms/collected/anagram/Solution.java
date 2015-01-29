package org.codeexample.algorithms.collected.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
	public ArrayList<String> anagrams(String[] strs) {
		// Start typing your Java solution below
		// DO NOT write main() function
		if (strs.length < 2)
			return new ArrayList();

		HashMap<String, ArrayList<String>> map = new HashMap();

		for (String str : strs) {
			String key = sortChars(str);
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<String>());
			}
			ArrayList<String> anagrams = map.get(key);
			anagrams.add(str);
		}

		ArrayList<String> result = new ArrayList();
		for (String key : map.keySet()) {
			ArrayList<String> anagrams = map.get(key);
			if (anagrams.size() > 1) {
				result.addAll(anagrams);
			}
		}

		return result;
	}

	public String sortChars(String s) {
		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
}