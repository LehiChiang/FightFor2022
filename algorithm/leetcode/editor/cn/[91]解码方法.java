package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class numDecodingsSolution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <=n; i++) {
            int digit = s.charAt(i - 1) - '0';
            if (digit != 0)
                dp[i] = dp[i] + dp[i - 1];
            if (i > 1 && (s.charAt(i - 2) - '0') * 10 + digit >= 10 && (s.charAt(i - 2) - '0') * 10 + digit <= 26)
                dp[i] = dp[i] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        numDecodingsSolution solution = new numDecodingsSolution();
        System.out.println(solution.numDecodings("10"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
