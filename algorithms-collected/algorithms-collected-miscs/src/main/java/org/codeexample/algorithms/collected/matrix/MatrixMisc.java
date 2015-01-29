package org.codeexample.algorithms.collected.matrix;

public class MatrixMisc {

	public static void main(String[] args) {
		int M[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 }, { 17, 18, 19, 20 }, };
		System.out.println("Given matrix is \n");

		System.out.println("\nDiagonal printing of matrix is \n");
		diagonalOrder(M);
	}

	// The main function that prints given matrix in diagonal order
	public static void diagonalOrder(int matrix[][]) {
		int ROW = 4, COL = 4;
		// There will be ROW+COL-1 lines in the output
		for (int line = 1; line <= (ROW + COL - 1); line++) {
			/*
			 * Get column index of the first element in this line of output. The
			 * index is 0 for first ROW lines and line - ROW for remaining lines
			 */
			int start_col = Math.max(0, line - ROW);

			/*
			 * Get count of elements in this line. The count of elements is
			 * equal to minimum of line number, COL-start_col and ROW
			 */
			int count = Math.min(line, Math.min((COL - start_col), ROW));

			/* Print elements of this line */
			for (int j = 0; j < count; j++)
				System.out.printf("%5d ",
						matrix[Math.min(ROW, line) - j - 1][start_col + j]);

			/* Ptint elements of next diagonal on next line */
			System.out.println("\n");
		}
	}

}
