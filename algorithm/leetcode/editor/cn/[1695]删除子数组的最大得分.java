package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class maximumUniqueSubarraySolution {
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0, maxScore = Integer.MIN_VALUE;
        int start = 0, end = 0;
        while (end < nums.length) {
             if (end > 0) {
                 set.remove(nums[start]);
                 sum -= nums[start];
                 start++;
             }
             while (end < nums.length && !set.contains(nums[end])) {
                 set.add(nums[end]);
                 sum += nums[end];
                 end++;
             }
             maxScore = Math.max(maxScore, sum);
        }
        return maxScore;
    }

    public static void main(String[] args) {
        maximumUniqueSubarraySolution solution = new maximumUniqueSubarraySolution();
        System.out.println(solution.maximumUniqueSubarray(new int[]{5,2,1,2,5,2,1,2,5}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
