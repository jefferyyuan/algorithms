package codebytes.deleteMiddleNode;

public class Code {
	private static void deleteMiddleNode(LinkedList.Node node) {
		if (node.next == null)
			return; // End node provided
		LinkedList.Node prev = node;
		node = node.next;

		while (node.next != null) {
			prev.value = node.value;
			prev = node;
			node = node.next;
		}
		prev.value = node.value;
		prev.next = null;
	}

	private static <T> LinkedList<T>.Node selectMiddleNode(LinkedList<T> ll,
			int index) {
		LinkedList<T>.Node node = ll.head;
		for (int i = 0; node.next != null && i <= index - 1; ++i) {
			node = node.next;
		}
		return node;
	}

	public static <T> LinkedList<T> buildList(T[] a) {
		LinkedList<T> ll = new LinkedList<>();
		for (T t : a) {
			ll.add(t);
		}
		return ll;
	}

	public static void main(String[] args) {
		Character array[] = new Character[] { 'A', 'E', 'I', 'O', 'U' };
		LinkedList<Character> ll = buildList(array);
		LinkedList<Character>.Node node = selectMiddleNode(ll, 2);
		deleteMiddleNode(node);
		System.out.println(ll);
	}
}
