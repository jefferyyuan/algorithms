package org.codeexample.collected.miscs.topn;

import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import static junit.framework.Assert.*;

public class HashMapSorterByValue {

	public class MaxSizeHashMap<K, V> extends LinkedHashMap<K, V> {
		private static final long serialVersionUID = 1L;
		private final int maxSize;

		public MaxSizeHashMap(int maxSize) {
			this.maxSize = maxSize;
		}

		protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
			return size() > maxSize;
		}
	}

	// http://stackoverflow.com/questions/2864840/treemap-sort-by-value
	public static <K extends Comparable<? super K>, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> getTopnbyValue(
			Map<K, V> map, int topn) {
		TreeSetTopnAscendingByValue<K, V> sortedEntries = new TreeSetTopnAscendingByValue<K, V>(
				topn);
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	/**
	 * inclusive for n and m, means for [2, 4], this with return 2nd, 3th, 4th
	 * 
	 * @param map
	 * @param start
	 * @param end
	 */
	public static <K extends Comparable<? super K>, V extends Comparable<? super V>> void getTopMNbyValue(
			Map<K, V> map, int start, int end) {
		TreeSetTopnAscendingByValue<K, V> sortedEntries = new TreeSetTopnAscendingByValue<K, V>(
				end);
		sortedEntries.addAll(map.entrySet());

		Iterator<Map.Entry<K, V>> iterator = sortedEntries.descendingIterator();

		while (iterator.hasNext()) {
			Map.Entry<K, V> entry = iterator.next();
			--start;
			if (start > 0)
				continue;
			System.out.println("vaule: " + entry);
		}
	}

	private static void testSortByValues() {
		TreeSetTopnAscendingByValue<String, Integer> sortedEntries = new TreeSetTopnAscendingByValue<String, Integer>(
				3);
		sortedEntries.add("David", 85);
		sortedEntries.add("Jeffery", 85);
		sortedEntries.add("Tom", 90);
		sortedEntries.add("Vivian", 95);
		sortedEntries.add("Jacky", 95);
		sortedEntries.add("Jacky", 95);

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

	private static void testSrotedByPeopleScore() {
		TreeSetTopnAscendingByValue<String, Double> sortedEntries = new TreeSetTopnAscendingByValue<String, Double>(
				2);
		Person p = new Person("David", 85);
		sortedEntries.add(p);
		p = new Person("Jeffery", 85);
		sortedEntries.add(p);
		p = new Person("Tom", 90);
		sortedEntries.add(p);
		p = new Person("Vivian", 95);
		sortedEntries.add(p);

		assertEquals(2, sortedEntries.size());
		Iterator<Entry<String, Double>> iterator = sortedEntries.iterator();
		assertEquals(90.0, iterator.next().getValue());
		assertEquals(95.0, iterator.next().getValue());
		System.out.println(sortedEntries);
	}

	private static void test() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("David", 85);
		map.put("Jeffery", 85);
		map.put("Tom", 90);
		map.put("Kevin", 90);
		map.put("Vivian", 95);
		// System.out.println(getTopnbyValue(map, 2));

		// should be 95, 90, 90, 85, 85
		// should be 90, 90, 85
		getTopMNbyValue(map, 2, 4);
		System.out.println("**********************");
		getTopMNbyValue(map, 4, 9);
		System.out.println("**********************");
		getTopMNbyValue(map, 1, 2);
	}

	public static void main(String[] args) throws Exception {

		// testSortByValues();
		// testSrotedByPeopleScore();
		test();
	}
}

interface IKeyValue<K, V> {
	public K getKey();

	public V getValue();
}

class Person implements IKeyValue<String, Double> {
	private String name;
	private double score;

	public Person(String name, double score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public String getKey() {
		return getName();
	}

	public Double getValue() {
		return score;
	}

	@Override
	public String toString() {
		return name + ":" + score;
	}
}

class TreeSetTopnAscendingByValue<K extends Comparable<? super K>, V extends Comparable<? super V>>
		extends TreeSet<Map.Entry<K, V>> {
	private static final boolean DEBUG = false;
	private static final long serialVersionUID = 1L;

	private int maxSize = Integer.MAX_VALUE;
	private Comparator<Map.Entry<K, V>> comparator;

	public TreeSetTopnAscendingByValue(Comparator<Map.Entry<K, V>> comparator,
			int maxSize) {
		super(comparator);
		this.comparator = comparator;
		this.maxSize = maxSize;
	}

	public TreeSetTopnAscendingByValue(int maxSize) {
		this(new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				int res = e1.getValue().compareTo(e2.getValue());
				if (res == 0) {
					res = e1.getKey().compareTo(e2.getKey());
				}
				return res;
			}
		}, maxSize);
	}

	public boolean add(K newKey, V newValue) {
		return add(new SimpleEntry<K, V>(newKey, newValue));
	}

	public boolean add(IKeyValue<K, V> obj) {
		return add(new SimpleEntry<K, V>(obj.getKey(), obj.getValue()));
	}

	@Override
	public boolean add(Entry<K, V> newValue) {
		boolean added = false;
		if (this.size() > maxSize)
			throw new RuntimeException("Code error, should never happen.");
		if (this.size() == maxSize) {
			// we can first check whether the map already contains this element, if
			// yes, ignore.
			// or we can first add, then if the size is increased, remove the first
			// element again, maybe the latter is better : we can run test to compare
			// it.
			Iterator<Entry<K, V>> iterator = this.iterator();
			Entry<K, V> currentMin = iterator.next();
			// only remove currentMin if newValue > currentMin. if equals, do nothing
			// for better performance.
			if (comparator.compare(newValue, currentMin) > 0) {
				added = super.add(newValue);
				if (DEBUG) {
					System.out.println("size: " + this.size() + ", add "
							+ newValue.toString() + ", remove: " + currentMin);
				}
				// the added element may already exist, if the map doesn't have this
				// element(added is true), remove currentMin
				// or we can us this.size() > maxSize
				if (added) {
					iterator = this.iterator();
					iterator.next();
					iterator.remove();
				}
			} else {
				// don't add newValue;
				if (DEBUG) {
					System.out.println("size: " + this.size() + ", Skip "
							+ newValue.toString());
				}
			}
		} else {
			added = super.add(newValue);
		}
		return added;
	}
}
