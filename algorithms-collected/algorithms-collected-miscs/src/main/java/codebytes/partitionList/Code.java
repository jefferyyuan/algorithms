package codebytes.partitionList;

import java.util.Arrays;

public class Code {
	public static <T extends Comparable<T>> void partitionList(
			LinkedList<T> ll, T value) {
		LinkedList<T>.Node turtle = ll.head, rabbit, equal = null;

		while (turtle != null) {
			if (turtle.value.compareTo(value) >= 0) {
				rabbit = turtle.next;
				int compare;
				equal = null;
				while (rabbit != null
						&& (compare = rabbit.value.compareTo(value)) >= 0) {
					if (compare == 0)
						equal = rabbit;
					rabbit = rabbit.next;
				}
				if (rabbit == null) {
					if (equal == null)
						return;
					else {
						T temp = equal.value;
						equal.value = turtle.value;
						turtle.value = temp;
					}
				} else {
					T temp = rabbit.value;
					rabbit.value = turtle.value;
					turtle.value = temp;
				}
			}
			turtle = turtle.next;
		}
	}

	public static <T> LinkedList<T> buildList(T[] a) {
		LinkedList<T> ll = new LinkedList<>();
		for (T t : a) {
			ll.add(t);
		}
		return ll;
	}

	public static void test(Integer[] array, int number) {
		System.out.println("\n" + Arrays.toString(array));
		System.out.println("Partitioning around " + number);
		LinkedList<Integer> ll = buildList(array);
		partitionList(ll, number);
		System.out.println(ll);
	}

	public static void main(String[] args) {
		Integer array[] = new Integer[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		test(array, 9);

		array = new Integer[] { 5, 2, 7, 3, 9, 1, 3, 6, 4, 8 };
		test(array, 3);

		array = new Integer[] { 5, 2, 7, 3, 9, 1, 0, 6, 4, 8 };
		test(array, 3);

		array = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		test(array, 3);

		array = new Integer[] { 2, 5, 6, 2, 5, 7, 9, 9, 4, 3, 5, 6, 8, 9, 5, 3,
				2, 11, 3, 4, 5, 6, 7, 8, 9, 6, 5, 6, 8, 9, 1, 0 };
		test(array, 6);

		array = new Integer[] {};
		test(array, 0);
	}
}
