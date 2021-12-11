package leetcode.editor.cn;
//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
// 示例 1：
//输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
//输出：4
// 示例 2：
//输入：matrix = [['0','1'],['1','0']]
//输出：1
// 示例 3：
//输入：matrix = [['0']]
//输出：0
// 提示：
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] 为 '0' 或 '1'
// Related Topics 数组 动态规划 矩阵 👍 950 👎 0


import utils.ArrayUtils;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class maximalSquareSolution {
    public int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxLength = 0;
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = matrix[i][0] - '0';
            maxLength = Math.max(maxLength, dp[i][0]);
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = matrix[0][j] - '0';
            maxLength = Math.max(maxLength, dp[0][j]);
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (matrix[i][j] - '0' == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength * maxLength;
    }

    public static void main(String[] args) {
        maximalSquareSolution solution = new maximalSquareSolution();
        System.out.println(solution.maximalSquare(new char[][]{
                {'1', '0', '0', '0', '0'},
                {'0', '1', '1', '1', '0'},
                {'0', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '0'}
        }));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
