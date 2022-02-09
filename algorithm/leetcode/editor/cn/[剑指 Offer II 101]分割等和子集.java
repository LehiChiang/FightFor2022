package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class canPartitionOffer2Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0)
            return false;
        sum /= 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        for (int i = 1; i <= nums.length; i++)
            dp[i][0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[nums.length][sum];
    }

    public static void main(String[] args) {
        canPartitionOffer2Solution solution = new canPartitionOffer2Solution();
        System.out.println(solution.canPartition(new int[]{2, 2, 1, 1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
