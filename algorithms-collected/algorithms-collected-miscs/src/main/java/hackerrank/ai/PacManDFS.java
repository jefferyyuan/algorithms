package hackerrank.ai;

import hackerrank.ai.bfs.Position;

import java.util.LinkedList;
import java.util.Scanner;

public class PacManDFS {

	static Position Pacman;
	static Position Food;
	static char[][] Grid;
	static boolean[][] VisitedNodes;

	static int _numVisitedNodes;

	static int numRows, numCol;
	static int _length;

	static LinkedList<Position> _path;
	static LinkedList<Position> _visit;

	static void nextMove() {
		DFS(Pacman.x, Pacman.y, 0);
	}

	static boolean DFS(int x, int y, int length) {
		if (Grid[x][y] == '%')
			return false;

		_numVisitedNodes++;
		_visit.addLast(new Position(x, y));
		VisitedNodes[x][y] = true;

		if (Grid[x][y] == '.') {
			_path.addFirst(new Position(x, y));
			_length = length;
			return true;
		}

		if (x != numRows - 1 && !VisitedNodes[x + 1][y]) {
			if (DFS(x + 1, y, length + 1)) {
				_path.addFirst(new Position(x, y));
				return true;
			}
		}

		if (y != numCol - 1 && !VisitedNodes[x][y + 1]) {
			if (DFS(x, y + 1, length + 1)) {
				_path.addFirst(new Position(x, y));
				return true;
			}
		}

		if (y != 0 && !VisitedNodes[x][y - 1]) {
			if (DFS(x, y - 1, length + 1)) {
				_path.addFirst(new Position(x, y));
				return true;
			}
		}

		if (x != 0 && !VisitedNodes[x - 1][y]) {
			if (DFS(x - 1, y, length + 1)) {
				_path.addFirst(new Position(x, y));
				return true;
			}
		}

		return false;
	}

	static void WriteSolution() {
		StringBuilder sb = new StringBuilder();

		sb.append(_numVisitedNodes).append("\n");

		for (Position p : _visit)
			sb.append(p.toString()).append("\n");

		sb.append(_length).append("\n");

		for (Position p : _path)
			sb.append(p.toString()).append("\n");

		System.out.println(sb.toString());
	}

	public static void main(String[] args) {

		_path = new LinkedList<Position>();
		_visit = new LinkedList<Position>();

		Scanner sc = new Scanner(System.in);

		Pacman = new Position(sc.nextInt(), sc.nextInt());
		Food = new Position(sc.nextInt(), sc.nextInt());

		_numVisitedNodes = 0;

		numRows = sc.nextInt();
		numCol = sc.nextInt();

		Grid = new char[numRows][numCol];
		VisitedNodes = new boolean[numRows][numCol];
		String line;
		for (int x = 0; x < numRows; x++) {
			line = sc.next();
			for (int y = 0; y < numCol; y++) {
				Grid[x][y] = line.charAt(y);
			}
		}

		sc.close();

		nextMove();
		WriteSolution();

	}

}

class Position {

	public int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(x).append(" ").append(y);
		return sb.toString();
	}
}