package leetcode.editor.cn;
//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。
// 示例 1：
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
//输出：true
// 示例 2：
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
//输出：false
// 提示：
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -10⁹ <= matrix[i][j] <= 10⁹ 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -10⁹ <= target <= 10⁹
// Related Topics 数组 二分查找 分治 矩阵 👍 884 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class searchMatrix2Solution {

    /**
     * 从左下角 或者 右上角走
     * 因为这两个位置上下左右行的元素是严格按照大小排布的
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] < target)
                row++;
            else col--;
        }
        return false;
    }

    public static void main(String[] args) {
        searchMatrix2Solution solution = new searchMatrix2Solution();
        System.out.println(solution.searchMatrix(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 20));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
