package leetcode.editor.cn;//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -10⁴ <= matrix[i][j], target <= 10⁴ 
// 
// Related Topics 数组 二分查找 矩阵 👍 544 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class searchMatrix1Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0, right = matrix.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid][0] == target)
                return true;
            if (matrix[mid][0] > target)
                right = mid;
            else left = mid + 1;
        }
        int rowNum = left == 0 ? 0 : left - 1;
        int leftInRow = 0, rightInRow = matrix[0].length;
        while (leftInRow < rightInRow) {
            int mid = leftInRow + (rightInRow - leftInRow) / 2;
            if (matrix[rowNum][mid] == target)
                return true;
            if (matrix[rowNum][mid] > target)
                rightInRow = mid;
            else leftInRow = mid + 1;
        }
        return false;
    }

    public static void main(String[] args) {
        searchMatrix1Solution solution = new searchMatrix1Solution();
        boolean res = solution.searchMatrix(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        }, 1);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
