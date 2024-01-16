package leetcode.editor.cn;

//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
// 示例 1：
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[7,4,1],[8,5,2],[9,6,3]]
// 示例 2：
//输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
// 示例 3：
//输入：matrix = [[1]]
//输出：[[1]]
// 示例 4：
//输入：matrix = [[1,2],[3,4]]
//输出：[[3,1],[4,2]]
// 提示：
// matrix.length == n 
// matrix[i].length == n 
// 1 <= n <= 20 
// -1000 <= matrix[i][j] <= 1000 
// 
// Related Topics 数组 数学 矩阵 👍 1073 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class rotateSolution {
    public static void main(String[] args) {
        rotateSolution solution = new rotateSolution();
        solution.rotate(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        });
    }

    public void rotate(int[][] matrix) {
        // 先0维度反转
        int len = matrix.length;
        for (int row = 0; row < len / 2; row++) {
            for (int col = 0; col < len; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[len - row - 1][col];
                matrix[len - row - 1][col] = temp;
            }
        }
        // 再对角线元素交换位置
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < row; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
