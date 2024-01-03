package leetcode.editor.cn;//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 矩阵 模拟 👍 893 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class spiralOrderSolution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++)
                res.add(matrix[top][i]);
            for (int j = top + 1; j <= bottom; j++)
                res.add(matrix[j][right]);
            if (top < bottom && left < right) {
                for (int i = right - 1; i >= left; i--)
                    res.add(matrix[bottom][i]);
                for (int j = bottom - 1; j > top; j--)
                    res.add(matrix[j][left]);
            }
            left += 1;
            right -= 1;
            top += 1;
            bottom -= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        spiralOrderSolution solution = new spiralOrderSolution();
        System.out.println(solution.spiralOrder(new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
