package leetcode.editor.cn;//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
// 
//
// 示例 2： 
//
// 
//
// 
//输入： heights = [2,4]
//输出： 4 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <=10⁵ 
// 0 <= heights[i] <= 10⁴ 
// 
// Related Topics 栈 数组 单调栈 👍 1682 👎 0


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class largestRectangleAreaSolution {
    public int largestRectangleArea(int[] heights) {
        int area = heights[0], n = heights.length;
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];
        Arrays.fill(rightMin, n);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                rightMin[stack.peek()] = i;
                stack.pop();
            }
            leftMin[i] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            area = Math.max(area, (rightMin[i] - leftMin[i] - 1) * heights[i]);
        }
        return area;
    }

    public static void main(String[] args) {
        largestRectangleAreaSolution solution = new largestRectangleAreaSolution();
//        System.out.println(solution.largestRectangleArea(new int[]{2,1,5,6,2,3}));
//        System.out.println(solution.largestRectangleArea(new int[]{2,4}));
//        System.out.println(solution.largestRectangleArea(new int[]{1,1}));
        System.out.println(solution.largestRectangleArea(new int[]{2,1,2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
