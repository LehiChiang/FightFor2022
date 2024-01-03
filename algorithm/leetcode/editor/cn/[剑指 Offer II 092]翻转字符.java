package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class minFlipsMonoIncrOffer2Solution {
    public int minFlipsMonoIncr(String s) {
        int[][] dp = new int[s.length()][2];
        dp[0][0] = s.charAt(0) == '0' ? 0 : 1;
        dp[0][1] = s.charAt(0) == '1' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            dp[i][0] = dp[i - 1][0] + (s.charAt(i) == '0' ? 0 : 1);
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + (s.charAt(i) == '1' ? 0 : 1);
        }
        return Math.min(dp[s.length() - 1][0], dp[s.length() - 1][1]);
    }

    public static void main(String[] args) {
        minFlipsMonoIncrOffer2Solution solution = new minFlipsMonoIncrOffer2Solution();
        System.out.println(solution.minFlipsMonoIncr("010110"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
