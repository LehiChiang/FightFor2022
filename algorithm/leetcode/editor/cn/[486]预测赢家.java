package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class PredictTheWinnerSolution {
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++)
            dp[i][i] = nums[i];
        for (int j = 1; j < len; j++) {
            for (int i = j - 1; i >= 0; i--) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;
    }

    private int dfs(int[] nums, int start, int end) {
        if (start > end)
            return 0;
        int chooseLeft = nums[start] - dfs(nums, start + 1, end);
        int chooseRight = nums[end] - dfs(nums, start, end - 1);
        return Math.max(chooseLeft, chooseRight);
    }

    public static void main(String[] args) {
        PredictTheWinnerSolution solution = new PredictTheWinnerSolution();
        System.out.println(solution.PredictTheWinner(new int[]{1,5,2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
