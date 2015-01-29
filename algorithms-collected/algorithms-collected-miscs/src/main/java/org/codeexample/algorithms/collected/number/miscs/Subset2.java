package org.codeexample.algorithms.collected.number.miscs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.google.common.primitives.Ints;

public class Subset2 {
	public static void main(String[] args) {
		int times = 5;
		List<Integer> tmp = new ArrayList<Integer>();
		for (int i = 0; i < times; i++) {
			tmp.add(i);
			tmp.add(i);
		}
		int[] num = Ints.toArray(tmp);
		Stopwatch st = Stopwatch.createStarted();
		System.out.println(subsetsWithDup2(num).size());

		System.out.println(st.elapsed(TimeUnit.MILLISECONDS));

	}

	public static List<List<Integer>> subsetsWithDup2(int[] num) {
		Arrays.sort(num);
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		subsetsWithDup2(num, 0, new ArrayList<Integer>(), solution);
		return solution;
	}

	public static void subsetsWithDup2(int[] num, int index,
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
		subsetsWithDup2(num, i, tempSolution, solution);

		// Add element num[i]
		tempSolution.add(num[index]);
		subsetsWithDup2(num, index + 1, tempSolution, solution);
		tempSolution.remove(tempSolution.size() - 1);
	}

	public static ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
		if (num == null)
			return null;

		Arrays.sort(num);

		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> prev = new ArrayList<ArrayList<Integer>>();

		for (int i = num.length - 1; i >= 0; i--) {

			// get existing sets
			if (i == num.length - 1 || num[i] != num[i + 1] || prev.size() == 0) {
				prev = new ArrayList<ArrayList<Integer>>(result);
				// for (int j = 0; j < result.size(); j++) {
				// prev.add(new ArrayList<Integer>(result.get(j)));
				// }
				// prev.addAll(result);
			}

			// add current number to each element of the set
			for (ArrayList<Integer> temp : prev) {
				temp.add(0, num[i]);
			}

			// add each single number as a set, only if current element is
			// different with previous
			if (i == num.length - 1 || num[i] != num[i + 1]) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(num[i]);
				prev.add(temp);
			}

			// add all set created in this iteration
			result.addAll(prev);
			// for (ArrayList<Integer> temp : prev) {
			// result.add(new ArrayList<Integer>(temp));
			// }
		}

		// add empty set
		result.add(new ArrayList<Integer>());

		return result;
	}
}
