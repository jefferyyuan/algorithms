package com.google.common.collect;

import java.util.Comparator;
import java.util.Iterator;

@SuppressWarnings("rawtypes")
public class TopnTreeMultimap<K extends Comparable, V extends Comparable>
		extends TreeMultimap<K, V> {
	private final boolean DEBUG = true;
	private static final long serialVersionUID = 1L;

	private int maxSize;

	public TopnTreeMultimap(Comparator<? super K> keyComparator,
			Comparator<? super V> valueComparator, int maxSize) {
		super(keyComparator, valueComparator);
		this.maxSize = maxSize;
	}

	public static <K extends Comparable, V extends Comparable> TopnTreeMultimap<K, V> create(
			int maxSize) {
		return new TopnTreeMultimap<K, V>(Ordering.natural(), Ordering.natural(),
				maxSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean put(K newKey, V newValue) {
		boolean added = false;
		if (this.size() > maxSize)
			throw new RuntimeException("Code error, should never happen.");
		if (this.size() == maxSize) {
			Iterator<K> keyIterator = this.keySet().iterator();
			K currentMinKey = keyIterator.next();
			if (newKey.compareTo(currentMinKey) <= 0) {
				if (DEBUG) {
					System.out.println("Ignore the put element: " + newKey + " : "
							+ newValue);
				}
			} else {
				added = super.put(newKey, newValue);

				if (added) {
					// remove the first element - the min
					Iterator entryiIterator = this.entryIterator();
					entryiIterator.next();
					entryiIterator.remove();
				}
			}
		} else {
			added = super.put(newKey, newValue);
		}
		return added;
	}

	public static void main(String[] args) throws Exception {
		TopnTreeMultimap<Integer, String> instance = TopnTreeMultimap.create(2);
		instance.put(85, "David");
		instance.put(85, "Jeffery");
		instance.put(90, "Tom");
		instance.put(95, "Vivian");
		instance.put(95, "Vivian");

		System.err.println(instance);
		// print {90=[Tom], 95=[Vivian]}
	}

}
