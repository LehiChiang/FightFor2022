package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;


//leetcode submit region begin(Prohibit modification and deletion)
class minimumTotalOffer2Solution {
    public static void main(String[] args) {
        minimumTotalOffer2Solution solution = new minimumTotalOffer2Solution();
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>() {{
            add(2);
        }});
        list.add(new ArrayList<>() {{
            add(3);
            add(4);
        }});
        list.add(new ArrayList<>() {{
            add(6);
            add(5);
            add(7);
        }});
        list.add(new ArrayList<>() {{
            add(4);
            add(1);
            add(8);
            add(3);
        }});
        System.out.println(solution.minimumTotal(list));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i] = triangle.get(i).get(i) + dp[i - 1];
            for (int j = i - 1; j > 0; j--)
                dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
            dp[0] += triangle.get(i).get(0);
        }
        int res = dp[0];
        for (int i = 1; i < n; i++)
            res = Math.min(res, dp[i]);
        return res;
    }

    public int minimumTotalDP(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i][0] = triangle.get(i).get(0) + dp[i - 1][0];
            for (int j = 1; j < i; j++)
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int res = dp[n - 1][0];
        for (int i = 1; i < n; i++)
            res = Math.min(res, dp[n - 1][i]);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
