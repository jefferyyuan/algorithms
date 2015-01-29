package codebytes.deleteZeros;

public class Code {

	public static void deleteZeros(int[][] a) {
		boolean rows[] = new boolean[a.length];
		boolean columns[] = new boolean[a[0].length];

		for (int i = 0; i < a.length; ++i) {
			for (int j = 0; j < a[0].length; ++j) {
				if (a[i][j] == 0) {
					rows[i] = true;
					columns[j] = true;
				}
			}
		}

		for (int i = 0; i < rows.length; ++i) {
			if (rows[i])
				for (int j = 0; j < columns.length; ++j)
					a[i][j] = 0;
		}
		for (int j = 0; j < columns.length; ++j) {
			if (columns[j])
				for (int i = 0; i < rows.length; ++i)
					a[i][j] = 0;
		}
	}

	public static void print(int[][] a) {
		System.out.println();
		for (int i = 0; i < a.length; ++i) {
			for (int j = 0; j < a[0].length; ++j) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[][] a = { { 1, 2, 9, 2, 4 }, { 2, 0, 3, 2, 1 }, { 2, 3, 4, 5, 0 },
				{ 3, 4, 6, 3, 4 } };
		deleteZeros(a);
		print(a);
	}
}