package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class combinationSum4Offer2Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i)
                    dp[i] = dp[i] + dp[i - num];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        combinationSum4Offer2Solution solution = new combinationSum4Offer2Solution();
        System.out.println(solution.combinationSum4(new int[]{9}, 3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
