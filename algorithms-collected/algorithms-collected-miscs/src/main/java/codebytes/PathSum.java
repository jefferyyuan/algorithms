package codebytes;

import java.util.ArrayList;
import java.util.List;

class PathSum {
	class Node {
		int value;
		Node left, right;

		Node() {
		}

		Node(int value) {
			this.value = value;
		}

		Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return Integer.toString(value);
		}
	}

	Node root;

	void helper(List<String> list, String s, Node origin, int sum, int value) {
		if (origin == null)
			return;
		sum += origin.value;
		if (sum == value) {
			String add = s;
			add += " " + origin.toString();
			list.add(add);
		}
		helper(list, s + " " + origin.toString(), origin.left, sum, value);
		helper(list, s + " " + origin.toString(), origin.right, sum, value);
	}

	void checkPaths(Node root, List<String> list, int value) {
		if (root == null)
			return;
		helper(list, "", root, 0, value);
		checkPaths(root.left, list, value);
		checkPaths(root.right, list, value);
	}

	public void printPathsForValue(int value) {
		List<String> paths = new ArrayList<>();
		checkPaths(root, paths, value);
		System.out.println("Paths: \n" + paths);
	}

	public void buildCustomTree() {
		root = new Node(1);
		setChildren(root, 2, 3);
		setChildren(root.left, 1, 2);
		setChildren(root.left.right, null, 3);
		setChildren(root.right, 1, 3);
		setChildren(root.right.left, null, 4);
		setChildren(root.right.right, null, 2);
		setChildren(root.right.right.right, 1, null);

		root = new Node(1);
		setChildren(root, 2, 3);
		setChildren(root.left, 1, 0);
		setChildren(root.right, 1, 0);
		setChildren(root.left.right, 0, 1);
		setChildren(root.right, 1, 0);
		setChildren(root.left.right, 0, 1);
		setChildren(root.left.right.left, 1, null);
		setChildren(root.right.left, 3, 2);
		setChildren(root.right.left.right, 1, null);

		// with -ve values as well
		root = new Node(1);
		setChildren(root, 2, 3);
		setChildren(root.left, 1, 0);
		setChildren(root.left.left, -1, null);
		setChildren(root.left.left.left, 1, 2);
		setChildren(root.left.left.left.right, -1, null);
		setChildren(root.left.right, 0, 1);
		setChildren(root.left.right.left, null, 1);
		setChildren(root.right, 1, 0);
		setChildren(root.right.left, 3, 2);
		setChildren(root.right.left.right, 1, null);
	}

	private void setChildren(Node node, Integer left, Integer right) {
		if (left != null)
			node.left = new Node(left);
		if (right != null)
			node.right = new Node(right);
	}

	public static void main(String[] args) {
		PathSum tree = new PathSum();
		tree.buildCustomTree();
		tree.printPathsForValue(4);
	}
}
