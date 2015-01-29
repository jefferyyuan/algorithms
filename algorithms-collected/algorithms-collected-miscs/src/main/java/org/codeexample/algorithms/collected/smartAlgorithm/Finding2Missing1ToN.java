package org.codeexample.algorithms.collected.smartAlgorithm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class Finding2Missing1ToN {

	public static void main(String[] args) {
		testFinding2Missing1ToN();
		System.out.println("'");
	}
	public static void testFinding2Missing1ToN() {
		int[] array = { 3, 5, 4 };// 1,2 is missing
		assertEquals(Lists.newArrayList(1, 2), finding2Missing1ToN(array));

		array = new int[] { 1, 2, 3 };
		assertEquals(finding2Missing1ToN(array), Lists.newArrayList(4, 5));

		array = new int[] { 2, 3, 4 };
		assertEquals(Lists.newArrayList(1, 5), finding2Missing1ToN(array));
	}

	public static List<Integer> finding2Missing1ToN(int[] array) {
		// use index i to indicate whether (i+1) exists
		// if current element is X, and X<array.length, then negative the iem at
		// index X-1.
		// Also remember whether array.length + 1, array.length+2 exist in the
		// array.
		boolean plus1Exist = false, plus2Exist = false;
		for (int i = 0; i < array.length; i++) {
			int curr = array[i];
			if (curr < 0) {
				curr = -curr; // or ~curr+1
			}
			if ((curr - 1) < array.length) {
				// convert arr[curr-1] to negative value
				array[curr - 1] = -array[curr - 1];
			} else {
				if (curr == (array.length + 1)) {
					plus1Exist = true;
				} else if (curr == (array.length + 2)) {
					plus2Exist = true;
				}
			}
		}

		// scan the array again, if ith value is negative, it means (i+1)
		// exists, if
		// ith is positive, means i+1 is missing. at the same time, we change
		// negative value to absolute value to revert to array's original status.
		List<Integer> missingItems = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			int curr = array[i];
			if (curr < 0) {
				array[i] = -array[i];
			} else {
				// i+1 is missing
				missingItems.add(i + 1);
			}
		}

		if (!plus1Exist) {
			missingItems.add(array.length + 1);
		}
		if (!plus2Exist) {
			missingItems.add(array.length + 2);
		}
		return missingItems;
	}

}
