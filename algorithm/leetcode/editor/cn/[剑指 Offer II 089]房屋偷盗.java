package leetcode.editor.cn;


//leetcode submit region begin(Prohibit modification and deletion)
class robOffer2Solution {
    public static void main(String[] args) {
        robOffer2Solution solution = new robOffer2Solution();
        System.out.println(solution.rob(new int[]{2, 1, 1, 2}));
        System.out.println(solution.rob(new int[]{2, 7, 9, 3, 1}));
    }

    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1)
            return nums[0];
        if (len == 2)
            return Math.max(nums[0], nums[1]);
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
