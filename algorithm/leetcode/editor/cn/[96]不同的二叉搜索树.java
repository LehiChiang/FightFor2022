package leetcode.editor.cn;

//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
// 示例 1：
//输入：n = 3
//输出：5
// 示例 2：
//输入：n = 1
//输出：1
// 提示：
// 1 <= n <= 19
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 1362 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class numTreesSolution {

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    private int extracted(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return count(1, n, dp);
    }

    private int count(int low, int high, int[][] dp) {
        if (low > high)
            return 1;
        if (dp[low][high] != 0)
            return dp[low][high];
        int res = 0;
        for (int i = low; i <= high; i++) {
            int leftTreeNum = count(low, i - 1, dp);
            int rightTreeNum = count(i + 1, high, dp);
            res += leftTreeNum * rightTreeNum;
        }
        dp[low][high] = res;
        return res;
    }

    public static void main(String[] args) {
        numTreesSolution solution = new numTreesSolution();
        System.out.println(solution.numTrees(3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
