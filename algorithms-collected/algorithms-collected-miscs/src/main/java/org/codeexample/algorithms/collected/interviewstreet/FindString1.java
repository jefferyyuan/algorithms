package org.codeexample.algorithms.collected.interviewstreet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author Suhaib Khan
 */
public class FindString1 {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int noOfStrings = Integer.parseInt(br.readLine());
		String[] strings = new String[noOfStrings];
		for (int i = 0; i < noOfStrings; i++) {
			strings[i] = br.readLine();
		}

		int noOfTests = Integer.parseInt(br.readLine());
		int[] tests = new int[noOfTests];
		for (int i = 0; i < noOfTests; i++) {
			tests[i] = Integer.parseInt(br.readLine());
		}

		HashMap<String, Integer> subStrings = new HashMap<>();
		for (int k = 0; k < noOfStrings; k++) {
			for (int i = 0; i < strings[k].length(); i++) {
				for (int j = i + 1; j <= strings[k].length(); j++) {
					// System.out.println("i = "+ i + " j = " + j);
					String substr = strings[k].substring(i, j);
					int count = 1;
					if (subStrings.containsKey(substr)) {
						count += subStrings.get(substr);
					}
					subStrings.put(substr, count);
				}
			}
		}

		ArrayList substrs = new ArrayList<>();
		substrs.addAll(subStrings.keySet());
		Collections.sort(substrs);
		for (int i = 0; i < noOfTests; i++) {
			int index = tests[i];
			if (index <= substrs.size()) {
				System.out.println(substrs.get(index - 1));
			} else {
				System.out.println("INVALID");
			}
		}
	}
}