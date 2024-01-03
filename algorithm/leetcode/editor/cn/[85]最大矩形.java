package leetcode.editor.cn;

//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
// 示例 1：
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：6
//解释：最大矩形如上图所示。
// 示例 2：
//输入：matrix = []
//输出：0
// 示例 3：
//输入：matrix = [["0"]]
//输出：0
// 示例 4：
//输入：matrix = [["1"]]
//输出：1
// 示例 5：
//输入：matrix = [["0","0"]]
//输出：0
// 提示：
// rows == matrix.length 
// cols == matrix[0].length 
// 1 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1'
// Related Topics 栈 数组 动态规划 矩阵 单调栈 👍 1140 👎 0


import utils.ArrayUtils;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class maximalRectangleSolution {
    // 竟然是单调栈问题
    public int maximalRectangle(char[][] matrix) {
        int res = 0;
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                heights[j] = matrix[i][j] - '0' == 0 ? 0 : heights[j] + 1;
            res = Math.max(res, getMaxArea(heights));
        }
        return res;
    }

    private int getMaxArea(int[] heights) {
        int maxArea = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < heights.length; i++) {
            int start, end;
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int popedIndex = stack.pop();
                start = stack.isEmpty() ? -1 : stack.peek();
                end = i;
                maxArea = Math.max(maxArea, (end - start - 1) * heights[popedIndex]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popedIndex = stack.pop();
            int start = stack.isEmpty() ? -1 : stack.peek();
            int end = heights.length;
            maxArea = Math.max(maxArea, (end - start - 1) * heights[popedIndex]);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        maximalRectangleSolution solution = new maximalRectangleSolution();
        System.out.println(solution.maximalRectangle(new char[][]{
                {'0', '1'},
                {'1', '0'}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
