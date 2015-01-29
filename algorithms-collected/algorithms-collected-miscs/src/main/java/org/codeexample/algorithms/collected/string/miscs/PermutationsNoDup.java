package org.codeexample.algorithms.collected.string.miscs;

import java.util.ArrayList;

public class PermutationsNoDup {
	public static void main(String[] args) {

		int[] num = { 1, 1, 2 };
		System.out.println(permuteUnique(num));

	}

	public static ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permuteUnique(num, 0, result);
		return result;
	}

	private static void permuteUnique(int[] num, int start,
			ArrayList<ArrayList<Integer>> result) {

		if (start >= num.length) {
			ArrayList<Integer> item = convertArrayToList(num);
			result.add(item);
		}

		for (int j = start; j <= num.length - 1; j++) {
			if (containsDuplicate(num, start, j)) {
				swap(num, start, j);
				permuteUnique(num, start + 1, result);
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
