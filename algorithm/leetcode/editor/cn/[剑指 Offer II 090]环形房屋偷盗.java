package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class rob2Offer2Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1)
            return nums[0];
        if (len == 2)
            return Math.max(nums[0], nums[1]);
        return Math.max(dp(nums, 0, nums.length - 2), dp(nums, 1, nums.length - 1));
    }

    private int dp(int[] nums, int start, int end) {
        int[] dp = new int[nums.length];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);
        for (int i = 2; i <= end - start; i++) {
            dp[i] = Math.max(dp[i - 1],  dp[i - 2] + nums[i + start]);
        }
        return dp[nums.length - 2];
    }

    public static void main(String[] args) {
        rob2Offer2Solution solution = new rob2Offer2Solution();
        System.out.println(solution.rob(new int[]{1,2,3,1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
