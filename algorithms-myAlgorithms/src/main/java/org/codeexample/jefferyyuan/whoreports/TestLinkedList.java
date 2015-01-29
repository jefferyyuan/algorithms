package org.codeexample.jefferyyuan.whoreports;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TestLinkedList {
	public static void main(String[] args) {

		LinkedList<Integer> list = new LinkedList<>(
				Arrays.asList(1, 2, 3, 4, 5));
		list.addFirst(0);
		while (!list.isEmpty()) {
			System.out.println(list.pollFirst());
		}

		list = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));

		list.addLast(6);
		while (!list.isEmpty()) {
			System.out.println(list.pollLast());
		}
		
		Queue<Integer> queue = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
		while(!queue.isEmpty())
		{
			System.out.println(queue.poll());
		}
	}
}
