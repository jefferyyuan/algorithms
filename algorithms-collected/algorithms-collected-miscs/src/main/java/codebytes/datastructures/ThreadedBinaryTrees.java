package codebytes.datastructures;

import java.util.Scanner;

class nodetype {

	int info;
	nodetype left; /* reference to left son */

	nodetype right; /* reference to right son */

	boolean rthread; /*
					 * rhtread is TRUE if right is null or non-null THREAD
					 */

}

public class ThreadedBinaryTrees {

	static nodetype maketree(int x) {
		nodetype p;

		p = getnode();
		p.info = x;
		p.left = null;
		p.right = null;
		p.rthread = true;
		return p;
	}

	static nodetype getnode() {
		return new nodetype();
	}

	static void setleft(nodetype p, int x) {
		nodetype q;

		if (p == null) {
			System.out.println("Void insertion\n");
		} else if (p.left != null) {
			System.out.println("Invalid insertion\n");
		} else {
			q = getnode();
			q.info = x;
			p.left = q;
			q.left = null;
			/* The inorder successor of node(q) is node(p) */
			q.right = p;
			q.rthread = true;
		}/* end if */

	}/* end setleft */

	static void setright(nodetype p, int x) {
		nodetype q, r;

		if (p == null) {
			System.out.println("Void insertion\n");
		} else if (!p.rthread) {
			System.out.println("Invalid insertion\n");
		} else {
			q = getnode();
			q.info = x;
			/* save the inorder successor of node(p) */
			r = p.right;
			p.right = q;
			p.rthread = false;
			q.left = null;
			/*
			 * the inorder successor of node(q) is the previous suuccessor of
			 * node(p)
			 */
			q.right = r;
			q.rthread = true;
		} /* end else */

	} /* end setright */

	static void intrav3(nodetype tree) {
		nodetype p, q;
		p = tree;
		do {
			q = null;
			while (p != null) { /* Traverse left branch */

				q = p;
				p = p.left;
			}/* end while */

			if (q != null) {
				System.out.print(q.info + " ");
				p = q.right;
				while (q.rthread && p != null) {
					System.out.print(p.info + " ");
					q = p;
					p = p.right;
				}/* end while */

			}/* end if */

		} while (q != null);
	}

	public static void main(String[] args) {
		nodetype ptree;
		nodetype p = null, q;
		int number;

		System.out.println("Binary Tree Traversal\n\n");
		System.out.println("Enter numbers :");
		Scanner scan = new Scanner(System.in);
		number = scan.nextInt();

		ptree = maketree(number);

		while ((number = scan.nextInt()) != -999) {
			p = q = ptree;
			while (q != null) {
				p = q;
				if (number < p.info)
					q = p.left;
				else if (number >= p.info) {
					if (!p.rthread) {
						q = p.right;
					}
					if (p.rthread) {
						setright(p, number);
						break;
					}
				}
			}

			if (number < p.info)
				setleft(p, number);
		}

		System.out.println("\nInorder traversal : ");
		intrav3(ptree);
	}
}