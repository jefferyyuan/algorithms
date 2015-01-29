package org.codeexample.algorithms.collected.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PairKDifference {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line1 = in.readLine();
		String line2 = in.readLine();
		int n = Integer.parseInt(line1.split(" ")[0]);
		int k = Integer.parseInt(line1.split(" ")[1]);
		List<Integer> list = new ArrayList<Integer>();
		String[] sArr = line2.split(" ");
		for (String s : sArr) {
			list.add(Integer.parseInt(s));
		}

		// Sort the array
		Collections.sort(list);
		int num = list.get(0);
		int l = 1;
		int h = n - 1;
		int i = 1;
		int count = 0;
		while (i < n) {
			// For 'num' search if 'num+k' exists using binary search
			while (l < h) {
				int mid = (l + h) / 2;
				int val = list.get(mid);
				// if (val - num < k) {
				// break;
				// }
				if (val == num + k) {
					count++;
					break;
				} else if (val < num + k) {
					l = mid;
				} else {
					h = mid;
				}
			}

			num = list.get(i);
			i++;
			l = i;
			h = n - 1;

		}

		System.out.println(count);
	}

}