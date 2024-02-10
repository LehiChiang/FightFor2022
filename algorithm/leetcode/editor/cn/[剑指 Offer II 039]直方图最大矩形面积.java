package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;


//leetcode submit region begin(Prohibit modification and deletion)
class largestRectangleAreaOffer2Solution {
    public static void main(String[] args) {
        largestRectangleAreaOffer2Solution solution = new largestRectangleAreaOffer2Solution();
        System.out.println(solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

    public int largestRectangleArea(int[] heights) {
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
}
//leetcode submit region end(Prohibit modification and deletion)
