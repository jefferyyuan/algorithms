package org.codeexample.algorithms.collected.number.miscs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.google.common.primitives.Ints;

public class Permutation2 {

	public static void main(String[] args) {
		int times = 5;
		List<Integer> tmp = new ArrayList<Integer>();
		for (int i = 0; i < times; i++) {
			tmp.add(i);
			tmp.add(i);
		}
		int[] num = Ints.toArray(tmp);
		Stopwatch st = Stopwatch.createStarted();
		System.out.println(permuteUnique1(num).size());

		System.out.println(st.elapsed(TimeUnit.MILLISECONDS));

		st = Stopwatch.createStarted();
		System.out.println(permuteUnique2(num).size());

		System.out.println(st.elapsed(TimeUnit.MILLISECONDS));
	}

	public static ArrayList<ArrayList<Integer>> permuteUnique2(int[] num) {
		if (num == null)
			return null;
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (num.length == 0)
			return result;
		Arrays.sort(num); // Sort the array in non-descending order
		recursivePermute(num, new boolean[num.length],
				new ArrayList<Integer>(), result);
		return result;
	}

	// If "current" is already a permutation of "num", add it to "result";
	// otherwise, append each unused number to "current", and recursively try
	// next unused number
	private static void recursivePermute(int[] num, boolean[] used,
			ArrayList<Integer> current, ArrayList<ArrayList<Integer>> result) {
		if (current.size() == num.length) { // "current" is already a
											// permutation of "num"
			result.add(new ArrayList<Integer>(current));
			return;
		}
		// Append each unused number to "current", and recursively try next
		// unused number
		for (int i = 0; i < num.length; i++) {
			if (i > 0 && !used[i - 1] && num[i] == num[i - 1])
				// Do not consider a duplicate number if its earlier appearance
				// has
				// not been considered yet
				continue;
			if (!used[i]) {
				// Append an unused number
				used[i] = true;
				current.add(num[i]);
				// Recursively append next unused number
				recursivePermute(num, used, current, result);
				// Get back to original state, get ready for appending another
				// unused number
				current.remove(current.size() - 1);
				used[i] = false;
			}
		}
	}

	public static ArrayList<ArrayList<Integer>> permuteUnique1(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permuteUnique1(num, 0, result);
		return result;
	}

	private static void permuteUnique1(int[] num, int start,
			ArrayList<ArrayList<Integer>> result) {

		if (start >= num.length) {
			ArrayList<Integer> item = convertArrayToList(num);
			result.add(item);
		}

		for (int j = start; j <= num.length - 1; j++) {
			if (containsDuplicate(num, start, j)) {
				swap(num, start, j);
				permuteUnique1(num, start + 1, result);
				swap(num, start, j);
			}
		}
	}

	private static ArrayList<Integer> convertArrayToList(int[] num) {
		ArrayList<Integer> item = new ArrayList<Integer>();
		for (int h = 0; h < num.length; h++) {
			item.add(num[h]);
		}
		return item;
	}

	private static boolean containsDuplicate(int[] arr, int start, int end) {
		for (int i = start; i <= end - 1; i++) {
			if (arr[i] == arr[end]) {
				return false;
			}
		}
		return true;
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
