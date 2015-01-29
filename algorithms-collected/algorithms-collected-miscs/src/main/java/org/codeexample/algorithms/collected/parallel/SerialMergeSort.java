package org.codeexample.algorithms.collected.parallel;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Random;

class SerialMergeSort<T> implements Runnable {

	protected List<T> list;
	protected Comparator<T> comp;
	protected boolean sorted;

	public SerialMergeSort(List<T> list, Comparator<T> comp) {
		this.list = list;
		this.comp = comp;
		this.sorted = false;
	}

	@Override
	public void run() {
		if (list.size() < 2)
			return;

		SerialMergeSort<T> a = new SerialMergeSort<T>(list.subList(0,
				list.size() / 2), comp);
		SerialMergeSort<T> b = new SerialMergeSort<T>(list.subList(
				list.size() / 2, list.size()), comp);

		a.run();
		b.run();

		merge(a.get(), b.get());
		this.sorted = true;
	}

	public boolean isSorted() {
		return this.sorted;
	}

	public List<T> get() {
		return list;
	}

	protected void merge(List<T> a, List<T> b) {

		int i = 0, j = 0, k = 0;

		while (i < a.size() && j < b.size()) {
			if (comp.compare(a.get(i), b.get(j)) < 0) {
				list.set(k, a.get(i));
				++k;
				++i;
			} else {
				list.set(k, b.get(j));
				++k;
				++j;
			}
		}
		while (i < a.size()) {
			list.set(k, a.get(i));
			++k;
			++i;
		}
		while (j < b.size()) {
			list.set(k, b.get(j));
			++k;
			++j;
		}
	}

	public static void main(String[] argv) {
		final int LENGTH = 100000;
		Random rand = new Random();
		ArrayList<Integer> array = new ArrayList<Integer>(LENGTH);

		for (int i = 0; i < LENGTH; ++i) {
			array.add(rand.nextInt());
		}

		Comparator<Integer> comp = new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a.compareTo(b);
			}

			@Override
			public boolean equals(Object a) {
				return this == a;
			}
		};

		SerialMergeSort<Integer> sort = new SerialMergeSort<Integer>(array,
				comp);
		System.out.println("About to sort.");
		long ellapsedNanos = System.nanoTime();
		sort.run();
		ellapsedNanos = System.nanoTime() - ellapsedNanos;
		System.out.println("Done sorting.");

		Iterator<Integer> a = array.iterator();
		Iterator<Integer> b = array.iterator();

		b.next();

		while (b.hasNext()) {
			if (a.next().compareTo(b.next()) > 0) {
				System.out.println("Sort failed!");
				return;
			}
		}
		System.out.println("Success, sorting took " + ellapsedNanos
				+ " nanoseconds.");
	}
}