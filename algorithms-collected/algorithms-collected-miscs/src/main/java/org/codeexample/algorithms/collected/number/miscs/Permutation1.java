package org.codeexample.algorithms.collected.number.miscs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.google.common.primitives.Ints;

public class Permutation1 {
	public static void main(String[] args) {
		int times = 10;
		List<Integer> tmp = new ArrayList<Integer>();
		for (int i = 0; i < times; i++) {
			tmp.add(i);
		}
		int[] num = Ints.toArray(tmp);
		// { 1, 2, 3 };
		Stopwatch st = Stopwatch.createStarted();
		System.out.println(permuteIter2(num).size());
//		permuteIter2(num);
		System.out.println(st.elapsed(TimeUnit.MILLISECONDS));

		st.reset();
		st.start();
		System.out.println(permuteIter1(num).size());
		System.out.println(st.elapsed(TimeUnit.MILLISECONDS));
	}

	public static ArrayList<LinkedList<Integer>> permuteIter1(int[] num) {
		if (num == null)
			return null;
		ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
		if (num.length == 0)
			return result;
		// Use LinkedList as the sublist type for better efficiency
		ArrayList<LinkedList<Integer>> resultWithLinkedList = new ArrayList<LinkedList<Integer>>();
		LinkedList<Integer> firstList = new LinkedList<Integer>();
		firstList.add(num[0]);
		resultWithLinkedList.add(firstList);
		// Given all permutations of num[0...i-1], insert num[i] to every
		// location of
		// each permutation so as to create all permutations of num[0...i]
		for (int i = 1; i < num.length; i++) {
			ArrayList<LinkedList<Integer>> newResult = new ArrayList<LinkedList<Integer>>();
			for (LinkedList<Integer> list : resultWithLinkedList) { // Each
																	// permutation
				for (int j = 0; j <= list.size(); j++) { // Every position of
															// the permutation
					LinkedList<Integer> temp = new LinkedList<Integer>(list);
					temp.add(j, num[i]);
					newResult.add(temp);
				}
			}
			resultWithLinkedList = newResult;
		}
		// Generate the result by converting the sublist type from LinkedList to
		// ArrayList
//		for (LinkedList<Integer> list : resultWithLinkedList) {
//			ArrayList<Integer> temp = new ArrayList<Integer>(list);
//			result.add(temp);
//		}

		return result;
	}

	public static ArrayList<ArrayList<Integer>> permuteIter2(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		// start from an empty list
		result.add(new ArrayList<Integer>());
		for (int i = 0; i < num.length; i++) {
			// list of list in current iteration of the array num
			ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>(num.length);

			for (ArrayList<Integer> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size() + 1; j++) {
					// + add num[i] to different locations
					// l.add(j, num[i]);
					ArrayList<Integer> temp = new ArrayList<Integer>(l);
					temp.add(j, num[i]);
					current.add(temp);
					// System.out.println(temp);
					// - remove num[i] add
					// l.remove(j);
				}
			}
			result = new ArrayList<ArrayList<Integer>>(current);
		}
		return result;
	}

}
