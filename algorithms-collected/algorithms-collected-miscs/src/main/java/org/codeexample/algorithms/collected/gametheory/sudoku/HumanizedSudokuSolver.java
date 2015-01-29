package org.codeexample.algorithms.collected.gametheory.sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * Sudoku solver using humanlike strategies
 * 
 * @author Pavel Micka
 */
public class HumanizedSudokuSolver {
	private SudokuField[][] sudoku;
	private SudokuStrategy[] strategies;

	/**
	 * Constucts a Sudoku solver
	 * 
	 * @param setting
	 *            initial setting, 0 denotes an empty cell
	 * @param strategies
	 *            array of strategies, which will be used
	 */
	HumanizedSudokuSolver(int[][] setting, SudokuStrategy... strategies) {
		this.strategies = strategies;
		sudoku = new SudokuField[setting.length][setting.length];
		for (int i = 0; i < setting.length; i++) {
			for (int j = 0; j < setting[i].length; j++) {
				if (setting[i][j] == 0) {
					sudoku[i][j] = new SudokuField();
				} else {
					sudoku[i][j] = new SudokuField(setting[i][j]);
				}
			}
		}
	}

	/**
	 * Returns a solution of the given Sudoku
	 * 
	 * @return solution
	 */
	public int[][] solve() {
		boolean succ;
		do {
			succ = false;
			for (SudokuStrategy s : strategies) {
				succ = s.solve(sudoku);
				if (succ) {
					break;
				}
			}
		} while (succ);

		int[][] solution = new int[sudoku.length][sudoku.length];
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku.length; j++) {
				solution[i][j] = sudoku[i][j].getSolution();
			}
		}

		return solution;
	}

	/**
	 * Testing main method Programs solves the Sudoku using several simple
	 * strategies and prints out the solution
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		int[][] setting = { // source: MF DNES 23.8.2010
		{ 0, 0, 4, 0, 3, 6, 9, 2, 7 }, { 1, 0, 0, 0, 0, 5, 0, 0, 0 },
				{ 0, 0, 0, 2, 0, 0, 0, 0, 4 }, { 0, 0, 5, 0, 0, 0, 0, 6, 0 },
				{ 6, 4, 0, 0, 0, 0, 0, 8, 5 }, { 0, 7, 0, 0, 0, 0, 2, 0, 0 },
				{ 5, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 7, 0, 0, 0, 0, 2 },
				{ 4, 3, 7, 9, 2, 0, 5, 0, 0 } };
		SudokuStrategy[] s = { new OneInARowStrategy(),
				new OneInAColumnStrategy(), new OneInAGroupStrategy() };
		HumanizedSudokuSolver solver = new HumanizedSudokuSolver(setting, s);
		int[][] solution = solver.solve();
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution.length; j++) {
				System.out.print(solution[i][j] + " ");
			}
			System.out.println("");
		}
	}
}

/**
 * Interface for a Sudoku strategies
 * 
 * @author Pavel Micka
 */
interface SudokuStrategy {

	/**
	 * Perform one step of the Sudoku solving
	 * 
	 * @param sudoku
	 *            array of the current state of the puzzle (0 denotes an emptz
	 *            field)
	 * @return true if the strategy was able to perform a step, false otherwise
	 */
	public boolean solve(SudokuField[][] sudoku);
}

/**
 * Sudoku strategy: every value must be unique in a row
 * 
 * @author Pavel Micka
 */
class OneInARowStrategy implements SudokuStrategy {

	public boolean solve(SudokuField[][] sudoku) {
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[i].length; j++) {
				int solution = sudoku[i][j].getSolution();
				if (solution != 0) { // if the cell is already solved
					for (int k = 0; k < sudoku[i].length; k++) { // iterate over
																	// the row
						if (sudoku[i][k].getSolution() == 0) { // if the cell is
																// not already
																// solved
							if (sudoku[i][k].hasCandidate(solution)) { // and
																		// has
																		// the
																		// current
																		// candidate
								sudoku[i][k].removeCandidate(solution); // than
																		// we
																		// will
																		// remove
																		// it
								System.out
										.println("OneInARow: Removing candidate "
												+ solution
												+ " at coordinates x: "
												+ k
												+ " y: " + i);
								return true; // return success
							}
						}
					}
				}
			}
		}
		return false;
	}
}

/**
 * Sudoku strategy: every value must be unique in a column
 * 
 * @author Pavel Micka
 */
class OneInAColumnStrategy implements SudokuStrategy {

	public boolean solve(SudokuField[][] sudoku) {
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[i].length; j++) {
				int solution = sudoku[j][i].getSolution();
				if (solution != 0) { // if the cell is already solved
					for (int k = 0; k < sudoku.length; k++) { // iterate over
																// the column
						if (sudoku[k][i].getSolution() == 0) { // if the cell is
																// not already
																// solved
							if (sudoku[k][i].hasCandidate(solution)) { // and
																		// has
																		// the
																		// current
																		// candidate
								sudoku[k][i].removeCandidate(solution); // than
																		// we
																		// will
																		// remove
																		// it
								System.out
										.println("OneInAColumn: Removing candidate "
												+ solution
												+ " at coordinates x: "
												+ i
												+ " y: " + k);
								return true; // return success
							}
						}
					}
				}
			}
		}
		return false;
	}
}

/**
 * Sudoku strategy: every value must be unique in a group
 * 
 * @author Pavel Micka
 */
class OneInAGroupStrategy implements SudokuStrategy {

	public boolean solve(SudokuField[][] sudoku) {
		int groupCount = (int) Math.sqrt(sudoku.length); // We assume that the
															// Sudoku setting is
															// a square
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[i].length; j++) {
				int solution = sudoku[i][j].getSolution();
				if (solution != 0) { // if the cell is already solved
					for (int k = i - (i % groupCount); k < i - (i % groupCount)
							+ groupCount; k++) {// rows
						for (int l = j - (j % groupCount); l < j
								- (j % groupCount) + groupCount; l++) {// columns
							if (sudoku[k][l].getSolution() == 0) { // if the
																	// cell is
																	// not
																	// already
																	// solved
								if (sudoku[k][l].hasCandidate(solution)) { // and
																			// has
																			// the
																			// current
																			// candidate
									sudoku[k][l].removeCandidate(solution);
									System.out
											.println("OneInAGroup: Removing candidate "
													+ solution
													+ " at coordinates x: "
													+ l
													+ " y: " + k);
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
}

/**
 * Represents a cell of a Sudoku
 * 
 * @author Pavel Micka
 */
class SudokuField {

	/**
	 * Set of candidates
	 */
	private Set<Integer> candidates;

	/**
	 * Creates a cell, candidates are numbers 1-9
	 */
	public SudokuField() {
		this.candidates = new HashSet<Integer>();
		for (int i = 1; i <= 9; i++) {
			candidates.add(i);
		}
	}

	/**
	 * Creates a cell with only one candidate (solution)
	 * 
	 * @param nr
	 *            reseni
	 */
	public SudokuField(int nr) {
		this.candidates = new HashSet<Integer>();
		candidates.add(nr);
	}

	/**
	 * Checks if the number is a candidate for this cell
	 * 
	 * @param nr
	 *            number
	 * @return true if the number is a candidate, false otherwise
	 */
	public boolean hasCandidate(int nr) {
		return candidates.contains(nr);
	}

	/**
	 * Removes the candidate
	 * 
	 * @param nr
	 *            candidate
	 */
	public void removeCandidate(int nr) {
		boolean succ = candidates.remove(nr);
		assert succ;
	}

	/**
	 * Returns a solution for this cell
	 * 
	 * @return solution or 0, if the cell is not solved yet
	 */
	public int getSolution() {
		if (candidates.size() != 1) {
			return 0;
		} else {
			return (Integer) (candidates.toArray()[0]);
		}
	}
}
