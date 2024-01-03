package leetcode.editor.cn;//给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
//
// 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满
//足条件的子矩阵，返回任意一个均可。 
//
// 注意：本题相对书上原题稍作改动 
//
// 示例： 
//
// 输入：
//[
//   [-1,0],
//   [0,-1]
//]
//输出：[0,1,0,1]
//解释：输入中标粗的元素即为输出所表示的矩阵 
//
// 
//
// 说明： 
//
// 
// 1 <= matrix.length, matrix[0].length <= 200 
// 
// Related Topics 数组 动态规划 矩阵 前缀和 👍 115 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class getMaxMatrixSolution {
    public int[] getMaxMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] compress = new int[n];
        int[] res = new int[4];
        int startRow = 0, startCol = 0;
        int maxSum = Integer.MIN_VALUE, sum;
        for (int i = 0; i < m; i++) {
            Arrays.fill(compress, 0);
            for (int j = i; j < m; j++) {
                sum = 0;
                for (int k = 0; k < n; k++) {
                    compress[k] += matrix[j][k];
                    if (sum > 0) {
                        sum += compress[k];
                    } else {
                        sum = compress[k];
                        startRow = i;
                        startCol = k;
                    }
                    if (sum > maxSum) {
                        maxSum = sum;
                        res[0] = startRow;
                        res[1] = startCol;
                        res[2] = j;
                        res[3] = k;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        getMaxMatrixSolution solution = new getMaxMatrixSolution();
        System.out.println(Arrays.toString(solution.getMaxMatrix(new int[][]{
                {9,-8,1,3,-2},
                {-3,7,6,-2,4},
                {6,-4,-4,8,-7}})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
