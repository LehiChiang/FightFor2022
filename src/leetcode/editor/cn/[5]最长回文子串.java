package leetcode.editor.cn;
//给你一个字符串 s，找到 s 中最长的回文子串。
// 示例 1：
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 示例 2：
//输入：s = "cbbd"
//输出："bb"
// 示例 3：
//输入：s = "a"
//输出："a"
// 示例 4：
//输入：s = "ac"
//输出："a"
// 提示：
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成
// Related Topics 字符串 动态规划 
// 👍 3901 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class longestPalindromeSolution {

    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxLen = 0;
        String maxPal = "";
        for (int len = 1; len <= n; len++) { // 遍历的是长度
            for (int start = 0; start < n; start++) { // 遍历的是回文字符串的起点
                int end = start + len - 1;
                if (end >= n)
                    break;
                dp[start][end] = s.charAt(start) == s.charAt(end) && ((len == 1) || (len == 2) || dp[start + 1][end - 1]);
                if (dp[start][end] && len > maxLen) {
                    maxPal = s.substring(start, end + 1);
                }
            }
        }
        return maxPal;
    }

    public String longestPalindrome_1(String s) {
        int n = s.length();
        if (n < 2)
            return s;
        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n; i++) {
                int end = i + len - 1;
                if (end >= n)
                    break;
                if (s.charAt(i) != s.charAt(end)) {
                    dp[i][end] = false;
                } else {
                    if (end - i < 3) {
                        dp[i][end] = true;
                    } else {
                        dp[i][end] = dp[i + 1][end - 1];
                    }
                }

                if (dp[i][end] && end - i + 1 > maxLen) {
                    maxLen = end - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        longestPalindromeSolution solution = new longestPalindromeSolution();
        String res = solution.longestPalindrome("ac");
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
