package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class minCutOffer2Solution {
    public int minCut(String s) {
        boolean[][] dp_state = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 1 || dp_state[j + 1][i - 1])) {
                    dp_state[j][i] = true;
                }
            }
        }

        int[] dp = new int[s.length() + 1];
        dp[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (dp_state[j][i - 1]) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[s.length()] - 1;
    }

    public static void main(String[] args) {
        minCutOffer2Solution solution = new minCutOffer2Solution();
        System.out.println(solution.minCut("aab"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
