package leetcode.editor.cn;//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 2： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
// Related Topics 深度优先搜索 广度优先搜索 动态规划 👍 414 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class movingCountSolution {
    public int movingCount(int m, int n, int k) {
        int[][] grid = new int[m][n];
        return dfs(grid, 0, 0, k);
    }

    private int dfs(int[][] grid, int i, int j, int k) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length ||
                getDigitSum(i) + getDigitSum(j) > k ||
                grid[i][j] == 1)
            return 0;
        grid[i][j] = 1;
        return dfs(grid, i - 1, j, k)
                + dfs(grid, i + 1, j, k)
                + dfs(grid, i, j - 1, k)
                + dfs(grid, i, j + 1, k) + 1;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        movingCountSolution solution = new movingCountSolution();
        System.out.println(solution.movingCount(3, 1, 0));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
