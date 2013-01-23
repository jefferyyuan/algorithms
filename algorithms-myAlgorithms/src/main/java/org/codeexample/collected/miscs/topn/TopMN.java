package org.codeexample.collected.miscs.topn;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

public class TopMN {

	public static List<Entry<String, Integer>> getTopMN(
			List<Entry<String, Integer>> originalData, int start, int end) {
		if (end < Math.round(originalData.size() / 2.0)) {
			return getMNUpperSide(originalData, start, end);
		} else {
			return getMNLowerSide(originalData, start, end);
		}
	}

	public static void main(String[] args) throws Exception {

		List<Entry<String, Integer>> data = new ArrayList<Entry<String, Integer>>();

		// 1 to 10
		// for (int i = 1; i < 11; i++) {
		// data.add(new SimpleEntry<String, Integer>(i + "", i));
		// }
		// // output: 10, 9
		// System.out.println(getTopMN(data, 1, 2));
		// // output: 7, 6
		// System.out.println(getTopMN(data, 4, 5));

		for (int i = 1; i < 11; i++) {
			data.add(new SimpleEntry<String, Integer>(i + "", i));
			data.add(new SimpleEntry<String, Integer>(i + "", i));
		}
		// System.out.println(getMNUpperSide(data, 1, 2));
		// System.out.println(getMNLowerSide(data, 1, 2));

		// bug here
		System.out.println(getMNLowerSide(data, 4, 5));
		// System.out.println(getTopMN(data, 4, 5));
	}

	private static List<Entry<String, Integer>> getMNUpperSide(
			List<Entry<String, Integer>> originalData, int start, int end) {
		// construct a treeset, size (end-0), comprised of largest (end-0)
		// element, sorted by the value, ascending

		// sort by value, ascending
		Comparator<Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
				int res = e1.getValue().compareTo(e2.getValue());
				if (res == 0) {
					res = e1.getKey().compareTo(e2.getKey());
				}
				return res;
			}
		};
		TreeSet<Entry<String, Integer>> largestTreeSet = new TreeSet<Map.Entry<String, Integer>>(
				comparator);
		Iterator<Map.Entry<String, Integer>> iterator = originalData.iterator();

		int treeSetSize = end;
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> newEntry = iterator.next();
			if (largestTreeSet.size() < treeSetSize) {
				largestTreeSet.add(newEntry);
			} else {
				Iterator<Entry<String, Integer>> innerIterator = largestTreeSet
						.iterator();
				Entry<String, Integer> currentMin = innerIterator.next();
				int value = comparator.compare(newEntry, currentMin);
				if (value > 0) {
					boolean added = largestTreeSet.add(newEntry);
					// it may already contains this element
					if (added) {
						// remove current min value
						innerIterator = largestTreeSet.iterator();
						innerIterator.next();
						innerIterator.remove();
					}
				}
			}
		}

		List<Entry<String, Integer>> result = new ArrayList<Entry<String, Integer>>();
		// get the range from start to end.
		Iterator<Entry<String, Integer>> keyIterator = largestTreeSet
				.descendingIterator();
		while (keyIterator.hasNext()) {
			Entry<String, Integer> entry = keyIterator.next();
			--start;
			if (start > 0)
				continue;
			result.add(entry);
		}
		return result;
	}

	private static List<Entry<String, Integer>> getMNLowerSide(
			List<Entry<String, Integer>> originaData, int start, int end) {
		// construct a treeset, size (size-start), comprised of least (size)
		// element, sorted by the value, descending

		// descending by the value: if e1 is greater than e2, return -1
		Comparator<Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
				int res = e1.getValue().compareTo(e2.getValue());
				if (res == 0) {
					res = e1.getKey().compareTo(e2.getKey());
				}
				return -res;
			}
		};
		TreeSet<Entry<String, Integer>> leastTreeSet = new TreeSet<Map.Entry<String, Integer>>(
				comparator);
		Iterator<Map.Entry<String, Integer>> iterator = originaData.iterator();

		int treeSetSize = originaData.size() - start + 1;
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> fieldEntry = iterator.next();
			if (leastTreeSet.size() < treeSetSize) {
				leastTreeSet.add(fieldEntry);
			} else {
				Iterator<Entry<String, Integer>> innerIterator = leastTreeSet
						.iterator();
				Entry<String, Integer> currentMax = innerIterator.next();
				int value = -comparator.compare(fieldEntry, currentMax);
				// if value<0, means fieldEntry less than currentMax, insert it,
				// otherwise ignore it.
				if (value < 0) {
					boolean added = leastTreeSet.add(fieldEntry);
					// it may already contain this element.
					if (added) {
						// remove current max value
						innerIterator = leastTreeSet.iterator();
						innerIterator.next();
						innerIterator.remove();
					}
				}
			}
		}

		List<Entry<String, Integer>> result = new ArrayList<Entry<String, Integer>>();
		// get the range from start to end.
		// int ignore = leastTreeSet.size() - (originaData.size() - end);
		int count = 0;
		int size = end - start + 1;
		Iterator<Entry<String, Integer>> keyIterator = leastTreeSet.iterator();
		while (keyIterator.hasNext()) {
			Entry<String, Integer> entry = keyIterator.next();
			// --ignore;
			// if (ignore > 0)
			// continue;
			result.add(entry);
			++count;
			if (count == size)
				break;
		}
		return result;
	}

}
