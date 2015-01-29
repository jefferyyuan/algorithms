package hackerrank.ai.astar;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	private static final char FOOD = '.';
	private static final char WALL = '%';
	
	private int X;
	private int Y;
	private char[][] board;
	
	private int[] food;
	private int[] pacman;
	
	public Solution(int X, int Y, char[][] board, int[] pacman, int[] food) {
		this.X = X;
		this.Y = Y;
		this.board = board;
		
		this.food = food;
		this.pacman = pacman;
	}
	
	public void explore() {
		Node food = UCS();
		
		List<Node> route = food.route();
		System.out.println(route.size() - 1);
		for (Node step : route) {
			step.logPosition();
		}
	}
	
	private Node UCS() {
		Queue<Node> queue = new PriorityQueue<Node>(X * Y, new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				return node1.cost() - node2.cost();
			}
		});
		
		boolean[][] explored = new boolean[X][Y];
		
		queue.add(new Node(pacman));
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			int x = node.position()[0];
			int y = node.position()[1];
			
			if (explored[x][y]) {
				continue;
			}
			explored[x][y] = true;
			
			if (node.isFood()) {
				return node;
			}
			
			if (isValidMove(x - 1, y)) {
				queue.add(new Node(new int[] {x - 1, y}, node));
			}
			if (isValidMove(x, y - 1)) {
				queue.add(new Node(new int[] {x, y - 1}, node));
			}
			if (isValidMove(x, y + 1)) {
				queue.add(new Node(new int[] {x, y + 1}, node));
			}
			if (isValidMove(x + 1, y)) {
				queue.add(new Node(new int[] {x + 1, y}, node));
			}
		}
		return null;
	}
	
	private boolean isValidMove(int x, int y) {
		return x >= 0 && y >= 0 && x < X && y < Y && board[x][y] != WALL;
	}
	
	private class Node {
		
		private int[] position;
		private Node parent;
		
		private int distance;
		
		private Node(int[] position) {
			this.position = position;
			this.distance = 0;
		}
		private Node(int[] position, Node parent) {
			this.position = position;
			this.parent = parent;
			this.distance = parent.distance() + 1;
		}
		
		private int[] position() {
			return position;
		}
		
		private List<Node> route() {
			List<Node> route = new ArrayList<Node>();
			if (parent != null) {
				route = parent.route();
			}
			route.add(this);
			return route;
		}
		
		private boolean isFood() {
			return board[position[0]][position[1]] == FOOD;
		}
		
		private void logPosition() {
			System.out.println(position[0] + " " + position[1]);
		}
		
		private int distance() {
			return distance;
		}
		
		private int estimation() {
			return (position[0] > food[0] ? position[0] - food[0] : food[0] - position[0]) +
					(position[1] > food[1] ? position[1] - food[1] : food[1] - position[1]);
		}
		
		private int cost() {
			return distance() + estimation();
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int[] pacman = new int[2];
		pacman[0] = scanner.nextInt();
		pacman[1] = scanner.nextInt();
		
		int[] food = new int[2];
		food[0] = scanner.nextInt();
		food[1] = scanner.nextInt();
		
		int X = scanner.nextInt();
		int Y = scanner.nextInt();
		
		char[][] board = new char[X][Y];
		for (int i = 0; i < X; i++) {
			board[i] = scanner.next().toCharArray();
		}
		
		scanner.close();
		
		Solution pacman4 = new Solution(X, Y, board, pacman, food);
		pacman4.explore();
	}
}
