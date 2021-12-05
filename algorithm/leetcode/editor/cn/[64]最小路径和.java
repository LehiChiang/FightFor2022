package leetcode.editor.cn;

//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
// 说明：每次只能向下或者向右移动一步。
// 示例 1：
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 示例 2：
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 提示：
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100
// Related Topics 数组 动态规划 矩阵 👍 1033 👎 0


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class minPathSumSolution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        int i = rows - 1, j = columns - 1;
        Deque<Integer> queue = new LinkedList<>();
        queue.offerFirst(grid[i][j]);
        while (i > 0 || j > 0) {
            int min_i = i - 1, min_j = j;
            if (min_i < 0) {
                min_i = 0;
                min_j = j - 1;
            }
            if (dp[min_i][min_j] > dp[i][j - 1]){
                min_i = i;
                min_j = j - 1;
            }
            queue.offerFirst(grid[min_i][min_j]);
            i = min_i;
            j = min_j;
        }
        System.out.println(queue);
        return dp[rows - 1][columns - 1];
    }

    public static void main(String[] args) {
        minPathSumSolution solution = new minPathSumSolution();
        System.out.println(solution.minPathSum(new int[][]{
                {1,3,1}, {1,5,1}, {4,2,1}
        }));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
