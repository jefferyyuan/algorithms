package codebytes.lca;

class WithoutParentFieldM2 {
	public class Node {
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
			return "Value: " + value;
		}
	}

	class Result {
		public Node node;
		public boolean isAncestor;

		public Result(Node n, boolean isAnc) {
			node = n;
			isAncestor = isAnc;
		}
	}

	Result commonAncestorHelper(Node root, Node p, Node q) {
		if (root == null)
			return new Result(null, false);
		if (root == p && root == q)
			return new Result(root, true);
		Result rx = commonAncestorHelper(root.left, p, q);
		if (rx.isAncestor)
			return rx;
		Result ry = commonAncestorHelper(root.right, p, q);
		if (ry.isAncestor)
			return ry;
		if (rx.node != null && ry.node != null) {
			return new Result(root, true);
		} else if (root == p || root == q) {
			boolean isAncestor = rx.node != null || ry.node != null;
			return new Result(root, isAncestor);
		} else
			return new Result(rx.node != null ? rx.node : ry.node, false);
	}

	Node commonAncestor(Node root, Node p, Node q) {
		Result r = commonAncestorHelper(root, p, q);
		if (r.isAncestor) {
			return r.node;
		}
		return null;
	}

	Node root;
	Node node1, node2;

	void makeCustomTree() {
		// Test Case: 3
		root = new Node(35);

		root = new Node(35);
		root.left = new Node(15);
		root.left.left = new Node(7);
		root.left.left.left = new Node(4);
		root.left.left.right = new Node(9);
		root.left.right = new Node(20);
		root.left.right.left = new Node(17);
		root.left.right.right = new Node(34);
		root.right = new Node(55);
		root.right.left = new Node(40);
		node1 = root.right.left.left = new Node(37);
		root.right.left.right = new Node(50);
		root.right.right = new Node(60);
		node2 = root.right.right.left = new Node(57);
		root.right.right.right = new Node(65);
	}

	public static void main(String[] args) {
		WithoutParentFieldM2 tree = new WithoutParentFieldM2();
		tree.makeCustomTree();
		System.out.println(tree.commonAncestor(tree.root, tree.node1,
				tree.node2));
	}
}

class WithoutParentFieldM1 {
	public class Node {
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
			return "Value: " + value;
		}
	}

	boolean found;

	class Result {
		boolean present = false;
		Node node;
	}

	void preOrder(Node nodeToFind, Node node) {
		if (node == null)
			return;
		if (node == nodeToFind)
			found = true;
		if (found)
			return;
		preOrder(nodeToFind, node.left);
		if (found)
			return;
		preOrder(nodeToFind, node.right);
	}

	boolean find(Node nodeToFind, Node node) {
		found = false;
		preOrder(nodeToFind, node);
		return found;
	}

	Node check(Node n1, Node n2) {
		if (n1 == null && n2 == null)
			return null;

		// Check if n1 and n2 are in the tree or not
		boolean n1InLeft = find(n1, root.left), n1InRight;
		if (n1InLeft)
			n1InRight = false;
		else
			n1InRight = find(n1, root.right);

		boolean n2InLeft = find(n2, root.left), n2InRight;
		if (n2InLeft)
			n2InRight = false;
		else
			n2InRight = find(n2, root.right);

		// If n1 is there and n2 isn't return n1
		// If n2 is there and n1 isn't return n2
		if ((n1InLeft || n1InRight) && !n2InLeft && !n2InRight)
			return n1;
		else if ((n2InLeft || n2InRight) && !n1InLeft && !n1InRight)
			return n2;

		// First case has been tested already to find n1 and n2
		if (n1InLeft == n2InRight)
			return root;

		// Go check left or right - root has already been checked
		if (n1InLeft && n2InLeft)
			return check(root.left, n1, n2);
		else
			return check(root.right, n1, n2);
	}

	Node check(Node node, Node n1, Node n2) {
		if (node == null)
			return null;
		boolean n1InLeft = find(n1, node.left);
		boolean n2InRight = find(n2, node.right);

		if (n1InLeft && n2InRight) {
			return node;
		}

		if (n1InLeft && !n2InRight) {
			return check(node.left, n1, n2);
		} else {
			return check(node.right, n1, n2);
		}
	}

	Node root;
	Node node1, node2;

	void makeCustomTree() {
		// Test Case: 3
		root = new Node(35);

		root = new Node(35);
		root.left = new Node(15);
		root.left.left = new Node(7);
		root.left.left.left = new Node(4);
		root.left.left.right = new Node(9);
		root.left.right = new Node(20);
		root.left.right.left = new Node(17);
		root.left.right.right = new Node(34);
		root.right = new Node(55);
		root.right.left = new Node(40);
		node1 = root.right.left.left = new Node(37);
		root.right.left.right = new Node(50);
		root.right.right = new Node(60);
		node2 = root.right.right.left = new Node(57);
		root.right.right.right = new Node(65);
	}

	public static void main(String[] args) {
		WithoutParentFieldM1 tree = new WithoutParentFieldM1();
		tree.makeCustomTree();
		System.out.println(tree.check(tree.node1, tree.node2));
	}
}

public class WithParentField {

	public class Node {
		int value;
		Node left, right, parent;

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
			return "Value: " + value;
		}
	}

	boolean found;

	void preOrder(Node nodeToFind, Node node) {
		if (node == null)
			return;
		if (node == nodeToFind)
			found = true;
		if (found)
			return;
		preOrder(nodeToFind, node.left);
		if (found)
			return;
		preOrder(nodeToFind, node.right);
	}

	boolean presentInThisSubtree(Node nodeToFind, Node node) {
		found = false;
		preOrder(nodeToFind, node);
		return found;
	}

	Node findAncestor(Node n1, Node n2) {
		if (n1 == null || n2 == null)
			return null;
		boolean n1PresentInTree = false, n2PresentInTree = false;
		Node parent = n1.parent;

		Node previous = n1;

		while (!found && parent != null && parent != n2) {
			if (previous == parent.left) {
				presentInThisSubtree(n2, parent.right);
			} else
				presentInThisSubtree(n2, parent.left);
			previous = parent;
			parent = parent.parent;
		}
		if (parent == n2 || found) {
			/*
			 * In case n1 and n2 are in a subtree but that subtree is not a
			 * subtree of the tree that we have
			 */
			while (parent != null && parent != root)
				parent = parent.parent;
			if (parent == root) {
				if (found)
					return previous;
				else
					return n2.parent;
			}
		} else {
			if (previous == root)
				n1PresentInTree = true;
			parent = n2.parent;
			if (parent != null)
				while (parent.parent != null && parent != n1)
					parent = parent.parent;
			if (parent == n1)
				return n1.parent;
			else if (parent == root) {
				System.out.println("Node 1 not present, Node 2 present");
				return n2;
			} else if (n1PresentInTree) {
				System.out.println("Node 1 present, Node 2 not present");
				return n1;
			}
		}
		System.out.println("Node 1 and Node 2 not present");
		return null;
	}

	Node root;
	Node node1, node2;

	void makeCustomTree() {
		// Test Case: 3
		root = new Node(35);

		node1 = new Node(4);
		node2 = new Node(65);

		addChildren(root, new Node(15), new Node(55));
		addChildren(root.left, new Node(7), new Node(20));
		addChildren(root.right, new Node(40), new Node(60));
		addChildren(root.left.left, node1, new Node(9));
		addChildren(root.left.right, new Node(17), new Node(34));
		addChildren(root.right.left, new Node(37), new Node(50));
		addChildren(root.right.right, new Node(57), node2);

		Node root2 = new Node(22);
		node1 = new Node(11);
		node2 = new Node(33);
		addChildren(root2, node1, node2);
		// node1 = new Node(222);
		// node2 = new Node(444);
	}

	void addChildren(Node parent, Node left, Node right) {
		parent.left = left;
		parent.right = right;
		if (left != null)
			left.parent = parent;
		if (right != null)
			right.parent = parent;
	}

	public static void main(String[] args) {
		WithParentField tree = new WithParentField();
		tree.makeCustomTree();
		Node node3 = tree.new Node(33);
		System.out.println(tree.findAncestor(tree.node1, tree.node2));
	}
}
