package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class findTargetSumWaysOffer2Solution {
    public static void main(String[] args) {
        findTargetSumWaysOffer2Solution solution = new findTargetSumWaysOffer2Solution();
        System.out.println(solution.findTargetSumWays(new int[]{1, 2, 1}, 3));
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        return sum < target ? 0 : find(nums, (target + sum) / 2);
//        return dfs(nums, 0, target);
    }

    private int dfs(int[] nums, int index, int target) {
        if (index == nums.length) {
            return target == 0 ? 1 : 0;
        }
        return dfs(nums, index + 1, target - nums[index]) + dfs(nums, index + 1, target + nums[index]);
    }

    private int find(int[] nums, int target) {
        int[][] dp = new int[nums.length + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0)
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[nums.length][target];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
