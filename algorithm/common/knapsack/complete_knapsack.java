package common.knapsack;

/**
 * complete Knapsack Problem
 */
public class complete_knapsack {

    /**
     * 完全背包问题
     * @param W
     * @param weights
     * @return
     */
    public int solutionForDP(int W, int[] weights) {
        int[][] dp = new int[weights.length + 1][W + 1];
        for (int i = 0; i <= weights.length; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= weights.length; i++) {
            for (int j = 1; j <= W; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    /**
                     * dp[i][j - weights[i - 1]]这个一定要注意，由于i的选取不止一次，所以
                     * i的情况可分为（1）一次都不选（2）选一次的情况
                     */
                    dp[i][j] = dp[i - 1][j] + dp[i][j - weights[i - 1]];
                }
            }
        }
        return dp[weights.length][W];
    }

    public static void main(String[] args) {
        complete_knapsack solution = new complete_knapsack();
        System.out.println(solution.solutionForDP(5, new int[]{1, 2, 5}));
    }
}
