package org.codeexample.collected.miscs.topn;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeSet;

public class TopnTester {
	// 100000. 0 seconds
	// private static final long count = 10000000;
	// private static final int topn = 100;

	private static List<Entry<Long, Long>> testTreeSetTopnAscendingByValue(
			long count, int topn) {
		long start = new Date().getTime();

		TreeSetTopnAscendingByValue<Long, Long> topnSet = new TreeSetTopnAscendingByValue<Long, Long>(
				topn);
		for (long i = 0; i < count; i++) {
			topnSet.add(i, i);
		}
		// ascending to descending
		Iterator<Entry<Long, Long>> iterator = topnSet.descendingIterator();
		List<Entry<Long, Long>> list = new ArrayList<>(topnSet.size());

		while (iterator.hasNext()) {
			Entry<Long, Long> entry = iterator.next();
			list.add(entry);
		}
		long end = new Date().getTime();

		System.out.println("testTreeSetTopnAscendingByValue: " + count + ":" + topn
				+ " takes " + (end - start) + " millseconds");
		return list;
	}

	private static List<Entry<Long, Long>> testTopnPriorityQueueSortByValue(
			long count, int topn) {

		long start = new Date().getTime();
		TopnPriorityQueueSortByValue<Long, Long> topnSet = new TopnPriorityQueueSortByValue<Long, Long>(
				topn);
		for (long i = 0; i < count; i++) {
			topnSet.offer(i, i);
		}

		TreeSet<SimpleEntry<Long, Long>> treeSet = new TreeSet<>(
				new Comparator<SimpleEntry<Long, Long>>() {
					@Override
					public int compare(SimpleEntry<Long, Long> o1,
							SimpleEntry<Long, Long> o2) {
						int result = o1.getValue().compareTo(o2.getValue());
						if (result == 0) {
							result = o1.getKey().compareTo(o2.getKey());
						}
						// reverse order
						return -result;
					}
				});

		Iterator<Entry<Long, Long>> iterator = topnSet.iterator();
		while (iterator.hasNext()) {
			treeSet.add((SimpleEntry<Long, Long>) iterator.next());
		}
		List<Entry<Long, Long>> list = new ArrayList<>(topnSet.size());

		Iterator<SimpleEntry<Long, Long>> iterator2 = treeSet.descendingIterator();
		while (iterator2.hasNext()) {
			Entry<Long, Long> entry = iterator2.next();
			list.add(entry);
		}
		// topnSet is not sorted.
		// sort it first
		// @SuppressWarnings("unchecked")
		// SimpleEntry<Long, Long>[] entries = topnSet.toArray(new SimpleEntry[0]);
		// List<SimpleEntry<Long, Long>> list = Arrays.asList(entries);
		// Collections.sort(list, new Comparator<SimpleEntry<Long, Long>>() {
		// @Override
		// public int compare(SimpleEntry<Long, Long> o1, SimpleEntry<Long, Long>
		// o2) {
		// int result = o1.getValue().compareTo(o2.getValue());
		// if (result == 0) {
		// result = o1.getKey().compareTo(o2.getKey());
		// }
		// // reverse order
		// return -result;
		// }
		// });
		long end = new Date().getTime();

		System.out.println("testTopnPriorityQueueSortByValue: " + count + ":"
				+ topn + " takes " + (end - start) + " millseconds");

		return list;
	}

	public static void main(String[] args) throws Exception {
		long count = 1000000000L;
		int topn = 100;
		for (int i = 0; i < 5; i++) {
			testTreeSetTopnAscendingByValue(count, topn);
			testTopnPriorityQueueSortByValue(count, topn);

			testTopnPriorityQueueSortByValue(count, topn);
			testTreeSetTopnAscendingByValue(count, topn);
		}
		// testTreeSetTopnAscendingByValue: 100000000:100 takes 14486 millseconds
		// testTopnPriorityQueueSortByValue: 100000000:100 takes 13827 millseconds
		// testTopnPriorityQueueSortByValue: 100000000:100 takes 13809 millseconds
		// testTreeSetTopnAscendingByValue: 100000000:100 takes 13406 millseconds

	}
}
