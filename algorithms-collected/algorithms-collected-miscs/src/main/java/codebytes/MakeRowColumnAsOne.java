package codebytes;

/* package whatever; // don't place package name! */

class MakeRowColumnAsOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] mat = new int[][] { { 1, 0, 0, 1 }, { 0, 0, 1, 0 },
				{ 0, 0, 0, 0 } };

		System.out.println("Input Matrix \n");
		printMatrix(mat);

		modifyMatrix(mat);

		System.out.println("Matrix after modification \n");
		printMatrix(mat);

	}

	public static void printMatrix(int[][] mat) {
		int i, j;
		for (i = 0; i < mat.length; i++) {
			for (j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j]);
			}
			System.out.println("\n");
		}
	}

	public static void modifyMatrix(int[][] mat) {
		int i, j;
		boolean paintRow1 = false, paintCol1 = false;
		i = 0;
		j = 0;
		while (j < mat[0].length && !paintRow1) {
			if (mat[0][j] == 1) {
				paintRow1 = true;
			}
			j++;
		}
		i = 0;
		j = 0;
		while (i < mat.length && !paintCol1) {
			if (mat[i][0] == 1) {
				paintCol1 = true;
			}
			i++;
		}

		for (i = 1; i < mat.length; i++)
			for (j = 1; j < mat[i].length; j++) {
				if (mat[i][j] == 1) {
					mat[i][0] = 1;
					mat[0][j] = 1;
				}
			}
		for (i = 1; i < mat.length; i++)
			for (j = 1; j < mat[i].length; j++) {
				if (mat[0][j] == 1 || mat[i][0] == 1) {
					mat[i][j] = 1;
				}
			}
		if (paintCol1) {
			for (i = 0; i < mat.length; i++)
				mat[i][0] = 1;
		}
		if (paintRow1) {
			for (j = 0; j < mat[0].length; j++)
				mat[0][j] = 1;
		}
	}
}
