package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class numDistinctOffer2Solution {

    public static void main(String[] args) {
        numDistinctOffer2Solution solution = new numDistinctOffer2Solution();
        System.out.println(solution.numDistinct("babgbag", "bag"));
    }

    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= m; j++)
            dp[0][j] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                else
                    dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[n][m];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
