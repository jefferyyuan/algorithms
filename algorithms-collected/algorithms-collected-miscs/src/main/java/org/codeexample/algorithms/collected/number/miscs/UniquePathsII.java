package org.codeexample.algorithms.collected.number.miscs;

public class UniquePathsII {

public static void print(int n) {
	if (n > 0) {
		System.out.println("hello");
		print(n - 1);
	}
	System.out.println("world");
}

	public static void main(String[] args) {
		print(5);
		int[][] obstacleGrid = { //
		{ 0, 0, 0 },//
				{ 0, 1, 0 }, //
				{ 0, 0, 0 } };
		// System.out.println(uniquePathsWithObstacles(obstacleGrid));
	}

	// 372ms for 43 test cases
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0
				|| obstacleGrid[0].length == 0)
			return 0;
		if (obstacleGrid[0][0] == 1)
			return 0;
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		int[] dp = new int[n + 1];
		dp[1] = 1; // Initialization: one unique path to the starting point
		// Compute the number of unique paths to obstacleGrid[i][j]
		// Normally, it equals to the sum of the number of unique paths to
		// obstacleGrid[i][j-1]
		// and that to obstacleGrid[i-1][j], except the case when the cell
		// contains obstacles
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (obstacleGrid[i][j] == 1) // Obstacle
					dp[j + 1] = 0; // Cannot reach a cell containing obstacle
				else
					// Empty space
					dp[j + 1] += dp[j];
			}
		}

		return dp[n];
	}
}