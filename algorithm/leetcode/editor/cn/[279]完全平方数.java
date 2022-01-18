package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class numSquaresSolution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        numSquaresSolution solution = new numSquaresSolution();
        System.out.println(solution.numSquares(11));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
