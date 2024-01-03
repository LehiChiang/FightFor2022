package leetcode.editor.cn;//给你一个正整数 n ，生成一个包含 1 到 n² 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
// Related Topics 数组 矩阵 模拟 👍 559 👎 0


import utils.ArrayUtils;

//leetcode submit region begin(Prohibit modification and deletion)
class generateMatrixSolution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int number = 0;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++)
                matrix[top][i] = ++number;
            for (int i = top + 1; i <= bottom; i++)
                matrix[i][right] = ++number;
            for (int i = right - 1; i >= left; i--)
                matrix[bottom][i] = ++number;
            for (int i = bottom - 1; i > top; i--)
                matrix[i][left] = ++number;
            top++;
            bottom--;
            left++;
            right--;
        }
        return matrix;
    }

    public static void main(String[] args) {
        generateMatrixSolution solution = new generateMatrixSolution();
        ArrayUtils.print2D(solution.generateMatrix(1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
