package geeksforgeeks;

import com.sun.webkit.graphics.Ref;

public class PruneTreePathSumLesserThanK {
	public static void main(String[] args) {
		int k = 45;
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		root.left.left.left = new Node(8);
		root.left.left.right = new Node(9);
		root.left.right.left = new Node(12);
		root.right.right.left = new Node(10);
		root.right.right.left.right = new Node(11);
		root.left.left.right.left = new Node(13);
		root.left.left.right.right = new Node(14);
		root.left.left.right.right.left = new Node(15);

		System.out.println("Tree before truncation\n");
		root.printInorder();

		root = prune(root, k); // k is 45

		System.out.println("\n\nTree after truncation\n");
		root.printInorder();
		// Tree before truncation
		// 8 4 13 9 15 14 2 12 5 1 6 3 10 11 7
		//
		// Tree after truncation
		// 4 9 15 14 2 1
	}

	private static class Ref {
		int value;

		public Ref(int value) {
			this.value = value;
		}
		@Override
		public String toString() {
			return value+"";
		}
	}

	private static class Node {
		int value;
		Node left, right;

		public Node(int value) {
			this.value = value;
			this.left = this.right = null;
		}

		public void printInorder() {
			if (this.left != null)
				this.left.printInorder();
			System.out.println(value);
			if (this.right != null)
				this.right.printInorder();
		}
		
		@Override
		public String toString() {
			return value + "";
		}
	}

	private static Node pruneUtil(Node root, int k, Ref sum) {
		// Base Case
		if (root == null)
			return null;

		// Initialize left and right sums as sum from root to
		// this node (including this node)
		Ref lsum = new Ref(sum.value + (root.value));
		Ref rsum = new Ref(lsum.value);

		// Recursively prune left and right subtrees
		root.left = pruneUtil(root.left, k, lsum);
		root.right = pruneUtil(root.right, k, rsum);

		// Get the maximum of left and right sums
		sum.value = Math.max(lsum.value, rsum.value);

		// If maximum is smaller than k, then this node
		// must be deleted
		if (sum.value < k) {
			root = null;
		}
		return root;
	}

	// A wrapper over pruneUtil()
	public static Node prune(Node root, int k) {
		int sum = 0;
		return pruneUtil(root, k, new Ref(sum));
	}
}
