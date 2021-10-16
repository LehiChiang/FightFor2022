package common.knapsack;

/**
 * 0-1's Knapsack Problem
 */
public class knapsack_0_1 {

    /**
     * 0，1背包问题的动态规划解法
     * @param N
     * @param W
     * @param wgt
     * @param val
     * @return
     */
    public int solutionForDP(int N, int W, int[] wgt, int[] val) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (w - wgt[i - 1] < 0) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - wgt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[N][W];
    }

    public static void main(String[] args) {
        knapsack_0_1 solution = new knapsack_0_1();
        System.out.println(solution.solutionForDP(3, 4, new int[]{2, 1, 3}, new int[]{4, 2, 3}));
    }
}
