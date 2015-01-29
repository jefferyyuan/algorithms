package org.codeexample.algorithms.collected.number.miscs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.google.common.primitives.Ints;

public class Subset1 {

	public static void main(String[] args) {
		int times = 5;
		List<Integer> tmp = new ArrayList<Integer>();
		for (int i = 0; i < times; i++) {
			tmp.add(i);
			tmp.add(i);
		}
		int[] num = Ints.toArray(tmp);
		Stopwatch st = Stopwatch.createStarted();
		System.out.println(subsets1(num).size());

		System.out.println(st.elapsed(TimeUnit.MILLISECONDS));

		st = Stopwatch.createStarted();
		System.out.println(subsets2(num).size());

		System.out.println(st.elapsed(TimeUnit.MILLISECONDS));
	}

	public static ArrayList<ArrayList<Integer>> subsets2(int[] S) {
		if (S == null)
			return null;

		Arrays.sort(S);

		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < S.length; i++) {
			ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();

			temp.addAll(result);
			// get sets that are already in result
			// for (ArrayList<Integer> a : result) {
			// temp.add(new ArrayList<Integer>(a));
			// }

			// add S[i] to existing sets
			for (ArrayList<Integer> a : temp) {
				a.add(S[i]);
			}
			// add S[i] only as a set
			temp.add(new ArrayList<Integer>(S[i]));
			result.addAll(temp);
		}

		// add empty set
		result.add(new ArrayList<Integer>());

		return result;
	}

	public static ArrayList<ArrayList<Integer>> subsets1(int[] S) {
		if (S == null)
			return null;

		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		// empty set is the subset of any set
		result.add(new ArrayList<Integer>());

		// sort array to statisfy the requirement of non-descending order
		Arrays.sort(S);

		dfsWorker(S, 0, result, new ArrayList<Integer>());

		return result;
	}

	public static void dfsWorker(int[] S, int position,
			ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp) {
		for (int i = position; i < S.length; i++) {
			temp.add(S[i]);

			// call dfsWorker() recursively
			dfsWorker(S, i + 1, result, temp);
			result.add((ArrayList<Integer>) temp.clone());

			// remove current element from temp before next interation
			temp.remove(Integer.valueOf(S[i]));
		}

	}
}
