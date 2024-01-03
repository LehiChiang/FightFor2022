package leetcode.editor.cn;
//给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
// 进阶：
// 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。 
// 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。 
// 你能想出一个仅使用常量空间的解决方案吗？
// 示例 1：
//输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
//输出：[[1,0,1],[0,0,0],[1,0,1]]
// 示例 2：
//输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
// 提示：
// m == matrix.length 
// n == matrix[0].length 
// 1 <= m, n <= 200 
// -231 <= matrix[i][j] <= 231 - 1
// Related Topics 数组 哈希表 矩阵 
// 👍 525 👎 0


import utils.ArrayUtils;

import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class setZeroesSolution {

    public void setZeroes(int[][] matrix) {
        boolean rowFlag = false, colFlag = false;
        for (int i = 0 ; i < matrix.length ; i++){
            if (matrix[i][0] == 0) {
                colFlag = true;
                break;
            }
        }

        for (int j = 0 ; j < matrix[0].length ; j++){
            if (matrix[0][j] == 0) {
                rowFlag = true;
                break;
            }
        }

        for (int i = 1 ; i < matrix.length ; i++)
            for (int j = 1 ; j < matrix[i].length ; j++){
                if (matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }

        for (int i = 1 ; i < matrix.length ; i++){
            if (matrix[i][0] == 0){
                for (int j = 1 ; j < matrix[i].length ; j++)
                    matrix[i][j] = 0;
            }
        }

        for (int j = 0 ; j < matrix[0].length ; j++){
            if (matrix[0][j] == 0){
                for (int i = 1 ; i < matrix.length ; i++)
                    matrix[i][j] = 0;
            }
        }

        if (colFlag)
            for (int i = 0 ; i < matrix.length ; i++)
                matrix[i][0] = 0;

        if (rowFlag)
            for (int j = 0 ; j < matrix[0].length ; j++)
                matrix[0][j] = 0;
    }

    public void setZeroes_1(int[][] matrix) {
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                if (matrix[i][j] == 0) {
                    list.add(new int[]{i, j});
                }

        for (int[] pair : list){
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[i].length; j++)
                    if (i == pair[0] || j == pair[1]) {
                        matrix[i][j] = 0;
                    }
        }
    }

    public static void main(String[] args) {
        setZeroesSolution solution = new setZeroesSolution();
        solution.setZeroes(new int[][]{{1, 2, 3}, {0, 7, 6}, {7, 8, 9}});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
