package org.codeexample.algorithms.collected.sort;

import java.util.Vector;

public class StringBucketSort {

	public static void main(String[] args) {
		bucketSort(new String[] { "ab", "abc", "defg" });
	}

	public static void bucketSort(String[] words) {
		int maxlength = 0;
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].toUpperCase();
			if (maxlength < words[i].length())
				maxlength = words[i].length();
		}

		for (int j = maxlength - 1; j >= 0; j--) {

			Vector<Vector<String>> map = new Vector<Vector<String>>();
			for (int i = 0; i < 27; i++) {
				map.add(null);
			}
			for (int i = 0; i < words.length; i++)// Add words of of length j or
													// greater to map(which is
													// bucket here)
			{
				if (words[i].length() > j) {
					int val = (int) words[i].charAt(j) - 65;
					if (map.get(val) != null) {
						map.get(val).add(words[i]);
					} else {
						Vector<String> vecot = new Vector<String>();
						vecot.add(words[i]);
						map.add(val, vecot);
					}
				} else// /Add words of of length<j to bucket 0
				{
					if (map.get(0) != null) {
						map.get(0).add(words[i]);
					} else {
						Vector<String> vecot = new Vector<String>();
						vecot.add(words[i]);
						map.add(0, vecot);
					}
				}
			}
			int count = 0;
			for (int i = 0; i < map.size(); i++) {
				if (map.get(i) != null) {
					for (int k = 0; k < map.get(i).size(); k++) {
						words[count] = map.get(i).get(k);
						count++;
					}
				}
			}
			System.out.println("Next set :");
			for (int i = 0; i < words.length; i++) {
				System.out.println(words[i]);
			}

		}

	}
}
