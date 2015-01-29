package org.codeexample.algorithms.collected.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsI {

	public List<List<Integer>> subsetsWithDup(int[] num) {
		Arrays.sort(num);
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		subsetsWithDup(num, 0, new ArrayList<Integer>(), solution);
		return solution;
	}

	public void subsetsWithDup(int[] num, int index,
			ArrayList<Integer> tempSolution, List<List<Integer>> solution) {
		if (index == num.length) {
			solution.add((List<Integer>) tempSolution.clone());
			return;
		}

		// Do not add element num[i]
		int value = num[index];
		int i = index;
		while (i < num.length && num[i] == value)
			i++;
		subsetsWithDup(num, i, tempSolution, solution);

		// Add element num[i]
		tempSolution.add(num[index]);
		subsetsWithDup(num, index + 1, tempSolution, solution);
		tempSolution.remove(tempSolution.size() - 1);
	}

	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
		if (S == null)
			return null;

		Arrays.sort(S);

		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < S.length; i++) {
			ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();

			// get sets that are already in result
			for (ArrayList<Integer> a : result) {
				temp.add(new ArrayList<Integer>(a));
			}

			// add S[i] to existing sets
			for (ArrayList<Integer> a : temp) {
				a.add(S[i]);
			}

			// add S[i] only as a set
			ArrayList<Integer> single = new ArrayList<Integer>();
			single.add(S[i]);
			temp.add(single);

			result.addAll(temp);
		}

		// add empty set
		result.add(new ArrayList<Integer>());

		return result;
	}

	public ArrayList<ArrayList<Integer>> mySubsets(int[] S) {
		if (S == null)
			return null;

		Arrays.sort(S);

		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < S.length; i++) {

			ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();

			// get sets that are already in result
			for (ArrayList<Integer> a : result) {
				temp.add(new ArrayList<Integer>(a));
			}

			// add S[i] to existing sets
			for (ArrayList<Integer> a : temp) {
				a.add(S[i]);
			}

			// add S[i] only as a set
			ArrayList<Integer> single = new ArrayList<Integer>();
			single.add(S[i]);
			temp.add(single);

			result.addAll(temp);
		}

		// add empty set
		result.add(new ArrayList<Integer>());

		return result;
	}
}
