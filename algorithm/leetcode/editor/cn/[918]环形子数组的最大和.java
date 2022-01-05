package leetcode.editor.cn;//给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
//
// 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 
//C[i+A.length] = C[i]） 
//
// 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, 
//k2 <= j 其中 k1 % A.length = k2 % A.length） 
//
// 
//
// 示例 1： 
//
// 输入：[1,-2,3,-2]
//输出：3
//解释：从子数组 [3] 得到最大和 3
// 
//
// 示例 2： 
//
// 输入：[5,-3,5]
//输出：10
//解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
// 
//
// 示例 3： 
//
// 输入：[3,-1,2,-1]
//输出：4
//解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
// 
//
// 示例 4： 
//
// 输入：[3,-2,2,-3]
//输出：3
//解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
// 
//
// 示例 5： 
//
// 输入：[-2,-3,-1]
//输出：-1
//解释：从子数组 [-1] 得到最大和 -1
// 
//
// 
//
// 提示： 
//
// 
// -30000 <= A[i] <= 30000 
// 1 <= A.length <= 30000 
// 
// Related Topics 队列 数组 分治 动态规划 单调队列 👍 270 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class maxSubarraySumCircularSolution {
    public int maxSubarraySumCircular(int[] nums) {
        int N = nums.length;
        int[] preSum = new int[2 * N + 1];
        for (int i = 0; i < 2 * N; i++)
            preSum[i + 1] = preSum[i] + nums[i % N];

        int res = nums[0];
        // 最小值队列
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(0);
        for (int i = 1; i < preSum.length; i++) {
            if (queue.peekFirst() < i - N)
                queue.pollFirst();

            res = Math.max(res, preSum[i] - preSum[queue.peekFirst()]);

            while (!queue.isEmpty() && preSum[queue.peekLast()] >= preSum[i])
                queue.pollLast();

            queue.offerLast(i);
        }
        return res;
    }

    public static void main(String[] args) {
        maxSubarraySumCircularSolution solution = new maxSubarraySumCircularSolution();
        System.out.println(solution.maxSubarraySumCircular(new int[]{3,-2,2,-3}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
