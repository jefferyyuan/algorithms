package hackerrank.ai;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class PacManBFS2 {
	class Node {
		public int x;
		public int y;
		public Node prev_node;
		public boolean visited;

		public Node(int _x, int _y) {
			x = _x;
			y = _y;
			prev_node = null;
			visited = false;
		}
	}

	/* Head ends here */
	static void nextMove(int x, int y, int pacman_x, int pacman_y, int food_x,
			int food_y, String[] grid) {
		// Your logic here
		ArrayList<Node> moves = new ArrayList<Node>();

		Node[][] nodes = new Node[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				nodes[i][j] = new Node(i, j);
			}
		}

		Node node = nodes[pacman_x][pacman_y], last_node = node;

		moves.add(node);
		node.visited = true;

		int index = 0;

		while (index < moves.size()) {
			node = moves.get(index);
			if (node.x > 0 && grid[node.x - 1].charAt(node.y) != '%'
					&& !(last_node = nodes[node.x - 1][node.y]).visited) {
				// UP
				moves.add(last_node);
				last_node.prev_node = node;
				last_node.visited = true;
				if (last_node.x == food_x && last_node.y == food_y)
					break;
			}
			if (node.y > 0 && grid[node.x].charAt(node.y - 1) != '%'
					&& !(last_node = nodes[node.x][node.y - 1]).visited) {
				// LEFT
				moves.add(last_node);
				last_node.prev_node = node;
				last_node.visited = true;
				if (last_node.x == food_x && last_node.y == food_y)
					break;
			}
			if (y > node.y + 1 && grid[node.x].charAt(node.y + 1) != '%'
					&& !(last_node = nodes[node.x][node.y + 1]).visited) {
				// RIGHT
				moves.add(last_node);
				last_node.prev_node = node;
				last_node.visited = true;
				if (last_node.x == food_x && last_node.y == food_y)
					break;
			}
			if (x > node.x + 1 && grid[node.x + 1].charAt(node.y) != '%'
					&& !(last_node = nodes[node.x + 1][node.y]).visited) {
				// DOWN
				moves.add(last_node);
				last_node.prev_node = node;
				last_node.visited = true;
				if (last_node.x == food_x && last_node.y == food_y)
					break;
			}
			index++;
		}

		if (last_node != nodes[food_x][food_y])
			return;

		LinkedList<Node> path = new LinkedList<Node>();
		while (last_node != null) {
			path.add(last_node);
			last_node = last_node.prev_node;
		}

		System.out.println(moves.size());
		for (Node nd : moves) {
			System.out.println(nd.x + " " + nd.y);
		}

		System.out.println(path.size() - 1);
		while ((node = path.pollLast()) != null) {
			System.out.println(node.x + " " + node.y);
		}

	}

	/* Tail starts here */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int pacman_x = in.nextInt();
		int pacman_y = in.nextInt();

		int food_x = in.nextInt();
		int food_y = in.nextInt();

		int x = in.nextInt();
		int y = in.nextInt();

		String grid[] = new String[x];

		for (int i = 0; i < x; i++) {
			grid[i] = in.next();
		}

		nextMove(x, y, pacman_x, pacman_y, food_x, food_y, grid);
	}
}