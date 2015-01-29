package org.codeexample.algorithms.collected.parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKArray {

	private static class Element<E extends Comparable<E>> implements
			Comparable<Element<E>> {
		private E value;
		private Iterator<E> iterator;

		@Override
		public int compareTo(Element<E> o) {
			return this.value.compareTo(o.value);
		}
	}

	/**
	 * Preconditions: List can't contain null
	 */
	public static <E extends Comparable<E>> List<E> merge(List<List<E>> lists) {
		if (lists == null || lists.isEmpty())
			return new ArrayList<>();

		PriorityQueue<Element<E>> pq = new PriorityQueue<>(lists.size()
		// ,new ElementComparator<E>() // or use external comparator
		);

		int allSize = 0;
		for (List<E> list : lists) {
			if (list != null && !list.isEmpty()) {
				Element<E> e = new Element<>();
				e.iterator = list.iterator();
				e.value = e.iterator.next();
				assert e.value != null;
				pq.add(e);

				allSize += list.size();
			}
		}
		List<E> result = new ArrayList<>(allSize);

		while (pq.size() > 1) {
			Element<E> e = pq.poll();
			assert e.value != null;
			result.add(e.value);
			Iterator<E> iterator = e.iterator;
			if (iterator.hasNext()) {
				e.value = iterator.next();
				assert e.value != null;
				pq.add(e);
			}
		}

		if (!pq.isEmpty()) {
			Element<E> e = pq.poll();
			result.add(e.value);
			while (e.iterator.hasNext()) {
				result.add(e.iterator.next());
			}
		}
		return result;
	}

	private static class ElementComparator<E extends Comparable<E>> implements
			Comparator<Element<E>> {
		public int compare(Element<E> o1, Element<E> o2) {
			return o1.value.compareTo(o2.value);
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> lists = new ArrayList<>();
		lists.add(Arrays.asList(1, 3, 5));
		lists.add(Arrays.asList(1, 3, 5, 8));
		lists.add(Arrays.asList(0, 10, 13, 15));
		System.out.println(merge(lists));
	}
}
