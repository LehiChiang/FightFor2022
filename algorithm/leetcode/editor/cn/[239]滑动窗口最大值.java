package leetcode.editor.cn;
//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
// 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
// 返回滑动窗口中的最大值。
// 示例 1：
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 示例 2：
//输入：nums = [1], k = 1
//输出：[1]
// 示例 3：
//输入：nums = [1,-1], k = 1
//输出：[1,-1]
// 示例 4：
//输入：nums = [9,11], k = 2
//输出：[11]
// 示例 5：
//输入：nums = [4,-2], k = 2
//输出：[4]
// 提示：
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 1 <= k <= nums.length
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 
// 👍 1120 👎 0


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class maxSlidingWindowSolution {
    public static void main(String[] args) {
        maxSlidingWindowSolution solution = new maxSlidingWindowSolution();
        int[] res = solution.maxSlidingWindow(new int[]{7, 2, 4}, 2);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return new int[]{};
        Deque<Integer> queue = new LinkedList<>();
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()])
                queue.pollLast();
            queue.offerLast(i);
            if (queue.peek() <= i - k)
                queue.pollFirst();
            if (i + 1 >= k)
                res[i - k + 1] = nums[queue.peekFirst()];
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
