package org.codeexample.algorithms.collected.misc;

/* package whatever; // don't place package name! */

/* Name of the class has to be "Main" only if the class is public. */
class Ideone {
	public static int getMaxValue_solution1(int[][] values) {
		int rows = values.length;
		int cols = values[0].length;

		int[][] maxValues = new int[rows][cols];

		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				int left = 0;
				int up = 0;

				if (i > 0) {
					up = maxValues[i - 1][j];
				}

				if (j > 0) {
					left = maxValues[i][j - 1];
				}

				maxValues[i][j] = Math.max(left, up) + values[i][j];
			}
		}

		return maxValues[rows - 1][cols - 1];
	}

	public static int getMaxValue_solution2(int[][] values) {
		int rows = values.length;
		int cols = values[0].length;

		int[] maxValues = new int[cols];
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				int left = 0;
				int up = 0;

				if (i > 0) {
					up = maxValues[j];
				}

				if (j > 0) {
					left = maxValues[j - 1];
				}

				maxValues[j] = Math.max(left, up) + values[i][j];
			}
		}

		return maxValues[cols - 1];
	}

	// ----------------------- TEST CODE -----------------------
	private static void test(String testName, int[][] values, int expected) {
		if (getMaxValue_solution1(values) == expected) {
			System.out.println(testName + ": solution1 passed.");
		} else {
			System.out.println(testName + ": solution1 FAILED.");
		}

		if (getMaxValue_solution2(values) == expected) {
			System.out.println(testName + ": solution2 passed.");
		} else {
			System.out.println(testName + ": solution2 FAILED.");
		}
	}

	private static void test1() {
		int[][] values = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int expected = 29;
		test("test1", values, expected);
	}

	private static void test2() {
		int[][] values = { { 1, 10, 3, 8 }, { 12, 2, 9, 6 }, { 5, 7, 4, 11 },
				{ 3, 7, 16, 5 } };
		int expected = 53;
		test("test2", values, expected);
	}

	private static void test3() {
		int[][] values = { { 1, 10, 3, 8 } };
		int expected = 22;
		test("test3", values, expected);
	}

	private static void test4() {
		int[][] values = { { 1 }, { 12 }, { 5 }, { 3 } };
		int expected = 21;
		test("test4", values, expected);
	}

	private static void test5() {
		int[][] values = { { 3 } };
		int expected = 3;
		test("test5", values, expected);
	}

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
	}
}