package org.codeexample.algorithms.collected.linkedlist;

public class ListPartition {
	public static ListNode partition(ListNode head, int x) {
		if (head == null)
			return null;

		ListNode fakeHead1 = new ListNode(0);
		ListNode fakeHead2 = new ListNode(0);
		fakeHead1.next = head;

		ListNode p = head;
		ListNode prev = fakeHead1;
		ListNode q = fakeHead2;

		while (p != null) { 
			if (p.val < x) {
				p = p.next;
				prev = prev.next;
			} else {

				q.next = p;
				prev.next = p.next;

				p = prev.next;
				q = q.next;
			}
		}

		// close the list
		q.next = null;

		prev.next = fakeHead2.next;

		return fakeHead1.next;
	}
}
