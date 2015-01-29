package geeksforgeeks;

public class RemoveShortPathNodes {
	public static void main(String[] args) {

		int k = 4;
		Node<Integer> root = new Node<>(1);
		root.left = new Node<>(2);
		root.right = new Node<>(3);
		root.left.left = new Node<>(4);
		root.left.right = new Node<>(5);
		root.left.left.left = new Node<>(7);
		root.right.right = new Node<>(6);
		root.right.right.left = new Node<>(8);

		root.printInorder();
		root = removeShortPathNodes(root, k);
		System.out.println("--------------");
		root.printInorder();
		// Inorder Traversal of Original tree
		// 7 4 2 5 1 3 8 6
		// Inorder Traversal of Modified tree
		// 7 4 2 1 3 8 6
	}

	private static class Node<T> {
		T value;
		Node<T> left, right;

		public Node(T value) {
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
	}

	public static <T> Node<T> removeShortPathNodes(Node<T> root, int k) {
		return removeShortPathNodesUtil(root, 1, k);
	}

	// Utility method that actually removes the nodes which are not
	// on the pathLen >= k. This method can change the root as well.
	private static <T> Node<T> removeShortPathNodesUtil(Node<T> root,
			int level, int k) {
		// Base condition
		if (root == null)
			return null;

		// Traverse the tree in postorder fashion so that if a leaf
		// node path length is shorter than k, then that node and
		// all of its descendants till the node which are not
		// on some other path are removed.
		root.left = removeShortPathNodesUtil(root.left, level + 1, k);
		root.right = removeShortPathNodesUtil(root.right, level + 1, k);

		// If root is a leaf node and it's level is less than k then
		// remove this node.
		// This goes up and check for the ancestor nodes also for the
		// same condition till it finds a node which is a part of other
		// path(s) too.
		if (root.left == null && root.right == null && level < k) {
			return null;
		}

		// Return root;
		return root;
	}
}
