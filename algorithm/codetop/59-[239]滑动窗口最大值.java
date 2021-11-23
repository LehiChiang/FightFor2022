package codetop;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[]{1, 3, -1, 0, -4, 5}, 3)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return new int[]{};
        }
        int[] res = new int[len - k + 1];
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            if (queue.peekFirst() <= i - k) {
                queue.pollFirst();
            }
            if (i >= k - 1) {
                res[i - k + 1] = nums[queue.peekFirst()];
            }
        }
        return res;
    }
}
