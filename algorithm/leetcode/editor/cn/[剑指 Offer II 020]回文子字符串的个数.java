package leetcode.editor.cn;


//leetcode submit region begin(Prohibit modification and deletion)
class countSubstringsOffer2Solution {
    public static void main(String[] args) {
        countSubstringsOffer2Solution solution = new countSubstringsOffer2Solution();
        System.out.println(solution.countSubstrings("abc"));
    }

    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = getParCounts(s, res, i, i);
            res = getParCounts(s, res, i, i + 1);
        }
        return res;
    }

    private int getParCounts(String s, int res, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            res++;
            left--;
            right++;
        }
        return res;
    }

    private int dynamicProgramming(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int res = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && ((j - i < 2 || dp[i + 1][j - 1]))) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
