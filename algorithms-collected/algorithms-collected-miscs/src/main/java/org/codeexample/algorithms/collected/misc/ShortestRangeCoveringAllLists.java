package org.codeexample.algorithms.collected.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestRangeCoveringAllLists {

	// Wrapper class to store line-number along with the integer data in the
	// Heap
	public static class DataAndLineNum {
		public int data;
		public int lineNum;
	}

	// Ascending Comparator
	public static class DateLineComparator implements
			Comparator<DataAndLineNum> {
		@Override
		public int compare(DataAndLineNum o1, DataAndLineNum o2) {
			return o1.data - o2.data;
		}
	}

	// Descending comparator
	public static class DateLineReverseComparator implements
			Comparator<DataAndLineNum> {
		@Override
		public int compare(DataAndLineNum o1, DataAndLineNum o2) {
			return o2.data - o1.data;
		}
	}

	public static void main(String[] args) {
		// Get some random input data
		int numLists = 5;
		int listSize = 4;
		Object lists[] = getRandomSortedLists(numLists, listSize);

		// Create ascending and descending comparators
		DateLineComparator cmp = new DateLineComparator();
		DateLineReverseComparator revCmp = new DateLineReverseComparator();

		// Create min-Heap and max-Heap by using PriorityQueue
		PriorityQueue<DataAndLineNum> minPQ = new PriorityQueue<DataAndLineNum>(
				numLists, cmp);
		PriorityQueue<DataAndLineNum> maxPQ = new PriorityQueue<DataAndLineNum>(
				numLists, revCmp);

		// Put first element of each list into min-Heap and max-Heap
		// Each element is converted from normal integer to wrapper class
		// DataAndLineNum before inserting
		for (int i = 0; i < numLists; i++) {
			ArrayList<Integer> lst = (ArrayList<Integer>) lists[i];

			DataAndLineNum info = new DataAndLineNum();
			info.data = lst.get(0);
			info.lineNum = i;

			minPQ.add(info);
			maxPQ.add(info);
		}

		// Heaps are initialized with first element of each list.
		// Now, remove one element from min-Heap and remove corresponding
		// element from max-Heap
		// From the removed element, get the line number
		// From the line-number, go directly to the list and take the next
		// element from it.

		int minDiff = 0;

		while (minPQ.size() > 0) {
			if (minPQ.size() == numLists) {
				int diff = maxPQ.peek().data - minPQ.peek().data;
				if (minDiff == 0 || diff < minDiff) {
					minDiff = diff;
					printCurrentPQ(minPQ, minDiff);
				}
			}

			DataAndLineNum smallest = minPQ.poll(); // remove smallest from
													// min-Heap
			maxPQ.remove(smallest); // remove same element from max-Heap

			// get next number from the line whose element is removed above
			int lineNum = smallest.lineNum;
			ArrayList<Integer> list = (ArrayList<Integer>) lists[lineNum];
			list.remove(0);

			if (list.size() > 0) {
				smallest.data = list.get(0); // re-use the wrapper object
												// smallest
				minPQ.add(smallest);
				maxPQ.add(smallest);
			}
		}
	}

	// Helper method to print the priority queue
	static void printCurrentPQ(PriorityQueue<DataAndLineNum> pq, int minDiff) {
		System.out.print("Diff = " + minDiff);
		System.out.println();

		DataAndLineNum[] arr = new DataAndLineNum[pq.size()];
		int i = 0;
		while (pq.size() > 0) {
			arr[i++] = pq.poll();
			System.out.println(arr[i - 1].data + "(Line " + arr[i - 1].lineNum
					+ ")");
		}
		for (DataAndLineNum d : arr)
			pq.add(d);
		System.out.println();
	}

	// Helper method to generate 'numLists' sorted lists, each of size
	// 'listSize'
	static Object[] getRandomSortedLists(int numLists, int listSize) {
		Object lists[] = new Object[numLists];
		for (int i = 0; i < numLists; i++) {
			ArrayList<Integer> lst = new ArrayList<Integer>();
			for (int j = 0; j < listSize; j++)
				lst.add((int) (10 + Math.random() * 89));

			Object[] arr = lst.toArray();
			Arrays.sort(arr);
			lst.clear();

			for (Object j : arr)
				lst.add((Integer) j);

			lists[i] = lst;
		}

		for (int i = 0; i < numLists; i++) {
			ArrayList<Integer> lst = (ArrayList<Integer>) lists[i];
			for (int j : lst)
				System.out.print(j + ", ");
			System.out.println();
		}
		System.out.println();
		return lists;
	}

}