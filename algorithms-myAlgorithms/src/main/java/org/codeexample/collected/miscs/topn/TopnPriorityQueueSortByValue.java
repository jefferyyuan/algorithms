package org.codeexample.collected.miscs.topn;

import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class TopnPriorityQueueSortByValue<K extends Comparable<? super K>, V extends Comparable<? super V>>
		extends PriorityQueue<Map.Entry<K, V>> {
	private static final long serialVersionUID = 1L;
	private static final boolean DEBUG = false;
	private int maxSize = Integer.MAX_VALUE;
	private static final int DEFAULT_INITIAL_CAPACITY = 11;

	public TopnPriorityQueueSortByValue(int maxSize) {

		super(DEFAULT_INITIAL_CAPACITY, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				int res = e1.getValue().compareTo(e2.getValue());
				if (res == 0) {
					res = e1.getKey().compareTo(e2.getKey());
				}
				return res;
			}
		});

		this.maxSize = maxSize;
	}

	public boolean offer(K key, V value) {
		return offer(new SimpleEntry<K, V>(key, value));
	}

	@Override
	public boolean offer(Map.Entry<K, V> newValue) {
		boolean added = false;
		if (this.size() > maxSize)
			throw new RuntimeException("Code error, should never happen.");
		if (this.size() == maxSize) {
			// Performance is not good, if we are sure there will be no duplicate data
			// if (this.contains(newValue))
			// return false;
			Entry<K, V> currentMin = this.peek();
			// only remove currentMin if newValue > currentMin. if equals, do nothing
			// for better performance.
			if (comparator().compare(newValue, currentMin) > 0) {
				added = super.offer(newValue);
				if (DEBUG) {
					System.out.println("size: " + this.size() + ", add "
							+ newValue.toString() + ", remove: " + currentMin);
				}
				// the added element may already exist, if it doesn't have this
				// element(added is true), remove the first one.
				if (added) {
					this.remove();
				}
			} else {
				// don't add newValue;
				if (DEBUG) {
					System.out.println("size: " + this.size() + ", Skip "
							+ newValue.toString());
				}
			}
		} else {
			added = super.offer(newValue);
		}
		return added;
	}

	public static void main(String[] args) throws Exception {
		TopnPriorityQueueSortByValue<String, Integer> sortedEntries = new TopnPriorityQueueSortByValue<String, Integer>(
				3);

		sortedEntries.offer("David", 85);
		sortedEntries.offer("Jeffery", 85);
		sortedEntries.offer("Tom", 90);
		sortedEntries.offer("Vivian", 95);
		sortedEntries.offer("Jacky", 95);
		sortedEntries.offer("Jacky", 95);

		System.out.println(sortedEntries);

		// test iterate
		Iterator<Entry<String, Integer>> iterator = sortedEntries.iterator();
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		sortedEntries.remove(new SimpleEntry<String, Integer>("Jacky", 95));
		System.out.println(sortedEntries);

	}

}
