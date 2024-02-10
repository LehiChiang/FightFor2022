package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class coinChangeOffer2Solution {
//    public int coinChange(int[] coins, int amount) {
//        int[] dp = new int[amount + 1];
//        for (int i = 1; i <= amount; i++) {
//            dp[i] = amount + 1;
//            for (int coin: coins) {
//                if (i >= coin)
//                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
//            }
//        }
//        return dp[amount] == amount + 1 ? -1 : dp[amount];
//    }

    public static void main(String[] args) {
        coinChangeOffer2Solution solution = new coinChangeOffer2Solution();
        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11));
    }

    private int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
            for (int change : coins) {
                if (i >= change) {
                    dp[i] = Math.min(dp[i], dp[i - change] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
