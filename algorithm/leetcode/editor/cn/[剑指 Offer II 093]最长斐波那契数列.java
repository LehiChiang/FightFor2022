package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class lenLongestFibSubseqOffer2Solution {

    
    public static void main(String[] args) {
        lenLongestFibSubseqOffer2Solution solution = new lenLongestFibSubseqOffer2Solution();
        System.out.println(solution.lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < arr.length; i++)
            index.put(arr[i], i);
        int ans = 0;
        int[][] dp = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                int num = arr[i] + arr[j];
                int k = index.getOrDefault(num, -1);
                if (k > 0 && dp[j][k] == 0) {
                    if (dp[i][j] == 0)
                        dp[i][j] = 2;
                    dp[j][k] = dp[i][j] + 1;
                    ans = Math.max(ans, dp[j][k]);
                }
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
