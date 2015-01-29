package org.codeexample.algorithms.collected.interviewstreet;

/**
 * In an unsorted array, given a number 'x', find the next smallest number. The
 * number 'x' does exist in the array. Array {62,50,91,32,60,63,17,36,55,61} Num
 * = 62 Result = 61
 * 
 * Num = 50 Result = 36
 * 
 * @author raju rama krishna
 *
 */
public class NextSmallest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.add(62);
		tree.add(50);
		tree.add(91);
		tree.add(32);
		tree.add(60);
		tree.add(63);
		tree.add(17);
		tree.add(36);
		tree.add(55);
		tree.add(61);

		int res = tree.find(62);
		System.out.println(res);
	}

}

class Tree {
	Node root;

	public void add(int val) {
		if (root == null) {
			root = new Node(val);
		} else {
			root = add(root, val);
		}
	}

	private Node add(Node node, int val) {
		if (node != null) {
			if (node.val > val) {
				node.left = add(node.left, val);
			} else {
				node.right = add(node.right, val);
			}
		} else {
			node = new Node(val);
		}
		return node;
	}

	public int find(int val) {
		int res = -1;
		Node node = root;
		while (node != null) {
			if (node.val == val) {
				break;
			} else if (node.val < val) {
				node = node.right;
			} else {
				node = node.left;
			}
		}

		if (node != null) {
			Node left = node.left;
			if (left != null) {
				node = left;
				while (node.right != null) {
					node = node.right;
				}
			}
		}

		if (node != null) {
			res = node.val;
		}
		return res;
	}
}

class Node {
	int val;
	Node left;
	Node right;

	public Node(int val) {
		this.val = val;
	}

	public String toString() {
		return String.valueOf(val);
	}
}