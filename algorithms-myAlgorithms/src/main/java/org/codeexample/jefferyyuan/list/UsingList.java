package org.codeexample.jefferyyuan.list;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UsingList {

	static class Person {
		private String name;

		@Override
		public String toString() {
			return "Person [name=" + name + "]";
		}

	}

	public static void swap(Person pa, Person pb) {
		Person tmp = pa;
		pa = pb;
		pb = tmp;
	}

	// 3 sum
	public int threeSumClosest(List<Integer> nums, int target) {
		Integer result = Integer.MAX_VALUE;

		// it copies elements into object[] then sort
		Collections.sort(nums);

		return result;
	}

	public static void main(String[] args) {
		Person pa = new Person();
		pa.name = "pa";

		Person pb = new Person();
		pb.name = "pb";
		System.out.println(pa);
		System.out.println(pb);

		System.out.println("-------------------");
		swap(pa, pb);

		System.out.println(pa);
		System.out.println(pb);
		Integer[] spam = new Integer[] { 1, 2, 3 };
		// O(1)
		List<Integer> list = Arrays.asList(spam);

		// list.add(1);
		// o(n)
		list.toArray();
	}

}
