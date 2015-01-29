package org.codeexample.algorithms.collected.misc;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
	public static void main(String[] args) {
		System.out.println(findMissingRanges(new int[] { 0, 1, 3, 50, 75 }, 0,
				99));
	}

	public static List<String> findMissingRanges(int[] vals, int start, int end) {
		List<String> ranges = new ArrayList<String>();
		int prev = start - 1;
		for (int i = 0; i < vals.length; ++i) {
			// int curr = (i == vals.length) ? end + 1 : vals[i];
			int curr = vals[i];
			if (curr - prev >= 2) {
				ranges.add(getRange(prev + 1, curr - 1));
			}
			prev = curr;
		}
		return ranges;
	}

	private static String getRange(int from, int to) {
		return (from == to) ? String.valueOf(from) : from + "->" + to;
	}
}
