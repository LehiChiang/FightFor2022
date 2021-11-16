package codetop;

import java.util.Arrays;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return new int[]{};
        }
        int[] res = new int[len - k + 1];
        int end = -1, start = 0, max = Integer.MIN_VALUE;
        while (end < len) {

        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[]{9,8,7,6}, 3)));
    }
}
