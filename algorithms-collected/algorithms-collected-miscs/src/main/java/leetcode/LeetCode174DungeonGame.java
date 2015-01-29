package leetcode;

/**
 * http://bookshadow.com/weblog/2015/01/07/leetcode-dungeon-game/
 *
 */
public class LeetCode174DungeonGame {
	public int calculateMinimumHP(int[][] dungeon) {
		int m = dungeon.length;
		int n = dungeon[0].length;
		// 其中D[i][j]代表可以保证骑士在进入房间(i,j)之前，探索其余地牢时能够存活下来的最小HP。
		// 显然D[0][0]就是我们随后需要的最终答案。因此，对于这个问题，我们需要从右下角到左上角填充表格。
		int[][] health = new int[m][n];
		health[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);
		for (int i = m - 2; i >= 0; i--) {
			health[i][n - 1] = Math.max(health[i + 1][n - 1]
					- dungeon[i][n - 1], 1);
		}
		for (int j = n - 2; j >= 0; j--) {
			health[m - 1][j] = Math.max(health[m - 1][j + 1]
					- dungeon[m - 1][j], 1);
		}
		for (int i = m - 2; i >= 0; i--) {
			for (int j = n - 2; j >= 0; j--) {
				int down = Math.max(health[i + 1][j] - dungeon[i][j], 1);
				int right = Math.max(health[i][j + 1] - dungeon[i][j], 1);
				health[i][j] = Math.min(right, down);
			}
		}
		return health[0][0];
	}
}