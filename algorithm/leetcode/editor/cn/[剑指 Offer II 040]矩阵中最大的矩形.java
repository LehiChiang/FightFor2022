package leetcode.editor.cn;

import utils.ArrayUtils;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class maximalRectangleOffer2Solution {
    public int maximalRectangle(String[] matrix) {
        if (matrix.length == 0)
            return 0;
        int[][] nums = convert2DArray(matrix);
        int res = 0;
        int[] heights = new int[matrix[0].length()];
        for (int row = 0; row < nums.length; row++) {
            for (int col = 0; col < nums[0].length; col++) {
                heights[col] = nums[row][col] != 0 ? heights[col] + nums[row][col] : 0;
            }
            res = Math.max(res, largest(heights));
        }
        return res;
    }

    public int largest(int[] heights) {
        int area = 0, n = heights.length;
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        Arrays.fill(rightMin, n);
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int index = stack.pop();
                rightMin[index] = i;
            }
            leftMin[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < heights.length; i++)
            area = Math.max(area, heights[i] * (rightMin[i] - leftMin[i] - 1));
        return area;
    }

    private int[][] convert2DArray(String[] matrix) {
        int[][] res = new int[matrix.length][matrix[0].length()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length(); j++) {
                res[i][j] = matrix[i].charAt(j) - '0';
            }
        }
        return res;
    }

    public static void main(String[] args) {
        maximalRectangleOffer2Solution solution = new maximalRectangleOffer2Solution();
        System.out.println(solution.maximalRectangle(new String[]{}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
