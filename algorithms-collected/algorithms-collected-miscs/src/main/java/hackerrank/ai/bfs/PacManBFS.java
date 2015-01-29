package hackerrank.ai.bfs;

import java.util.LinkedList;
import java.util.Scanner;

public class PacManBFS {

	static Position Pacman;
	static Position Food;
	static char[][] Grid;
	static boolean[][] VisitedNodes;

	static int _numVisitedNodes;

	static int numRows, numCol;

	static int _length;
	static boolean _found;

	static LinkedList<Position> _path;
	static LinkedList<Position> _visit;
	static LinkedList<Position> _queue;

	static void nextMove() {
		_queue.add(Pacman);
		while (!_found) {
			Visit(_queue.pollFirst());
		}
	}

	static void Visit(Position p) {
		if (Grid[p.Abs][p.Ord] == '%')
			return;
		if (VisitedNodes[p.Abs][p.Ord])
			return;

		_numVisitedNodes++;
		_visit.addLast(p);

		VisitedNodes[p.Abs][p.Ord] = true;

		if (Grid[p.Abs][p.Ord] == '.') {
			_found = true;
			p.BuildPath(_path);
			_length = _path.size() - 1;
		}

		p.Visit(_queue);
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
		_queue = new LinkedList<Position>();

		Scanner sc = new Scanner(System.in);

		Pacman = new Position(sc.nextInt(), sc.nextInt(), null);
		Food = new Position(sc.nextInt(), sc.nextInt(), null);

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

	public int Abs, Ord;
	public Position Father;

	public Position(int x, int y, Position father) {
		Abs = x;
		Ord = y;
		Father = father;
	}

	public void Visit(LinkedList<Position> queue) {
		if (Abs != 0 && !PacManBFS.VisitedNodes[Abs - 1][Ord])
			queue.addLast(new Position(Abs - 1, Ord, this));

		if (Ord != 0 && !PacManBFS.VisitedNodes[Abs][Ord - 1])
			queue.addLast(new Position(Abs, Ord - 1, this));

		if (Ord != PacManBFS.numCol - 1
				&& !PacManBFS.VisitedNodes[Abs][Ord + 1])
			queue.addLast(new Position(Abs, Ord + 1, this));

		if (Abs != PacManBFS.numRows - 1
				&& !PacManBFS.VisitedNodes[Abs + 1][Ord])
			queue.addLast(new Position(Abs + 1, Ord, this));
	}

	public void BuildPath(LinkedList<Position> path) {
		path.addFirst(this);
		if (Father != null)
			Father.BuildPath(path);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Abs).append(" ").append(Ord);
		return sb.toString();
	}
}
