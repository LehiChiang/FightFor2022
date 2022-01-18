package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class findTargetSumWaysSolution {

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        return sum < target || (sum & 1) != (target & 1) ? 0 : find(nums, (target + sum) / 2);
    }

    private int find(int[] nums, int target) {
        if (target < 0)
            return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = target; j >= nums[i - 1]; j--) {
                dp[j] += dp[j - nums[i - 1]];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        findTargetSumWaysSolution solution = new findTargetSumWaysSolution();
        System.out.println(solution.findTargetSumWays(new int[]{1,1,1,1,1}, 3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
