package org.codeexample.algorithms.collected.number.miscs;

import java.util.*;

public class LISPatienceSort {
	public static void main(String[] args) {
		Integer[] ns = {3, 2, 6, 4, 5, 1};
		sort(ns);
		System.out.println(Arrays.toString(ns));
	}
	public static <E extends Comparable<? super E>>  List<E> sort(E[] n) {
		List<Pile<E>> piles = new ArrayList<Pile<E>>();
		// sort into piles
		for (E x : n) {
			Pile<E> newPile = new Pile<E>();
			newPile.push(x);
			int i = Collections.binarySearch(piles, newPile);
			if (i < 0)
				i = ~i; // i=-i-1;
			if (i != piles.size())
				piles.get(i).push(x);
			else
				piles.add(newPile);
		}
		
		List<E> result = new ArrayList<E>();

		// get the 
		// priority queue allows us to retrieve least pile efficiently
		PriorityQueue<Pile<E>> heap = new PriorityQueue<Pile<E>>(piles);
		for (int c = 0; c < n.length; c++) {
			Pile<E> smallPile = heap.poll();
			n[c] = smallPile.pop();
			if (!smallPile.isEmpty())
				heap.offer(smallPile);
		}
		assert (heap.isEmpty());
		
		System.out.println(heap);
	}

	private static class Pile<E extends Comparable<? super E>> extends Stack<E>
			implements Comparable<Pile<E>> {
		public int compareTo(Pile<E> y) {
			return peek().compareTo(y.peek());
		}
	}
}