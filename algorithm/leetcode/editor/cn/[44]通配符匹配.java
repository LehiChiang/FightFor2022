package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class isMatchSolution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*')
                dp[0][j] = true;
            else break;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (Character.isLetter(p.charAt(j - 1)))
                    dp[i][j] = s.charAt(i - 1) == p.charAt(j - 1) && dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '?')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*')
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        isMatchSolution solution = new isMatchSolution();
        System.out.println(solution.isMatch("adceb", "*a*b"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
