package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class partitionOffer2Solution {

    private List<List<String>> res;
    private boolean[][] dp;


    public static void main(String[] args) {
        partitionOffer2Solution solution = new partitionOffer2Solution();
        System.out.println(Arrays.deepToString(solution.partition("google")));
    }

    public String[][] partition(String s) {
        res = new ArrayList<>();
        char[] charArray = s.toCharArray();
        dp = new boolean[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (charArray[i] == charArray[j] && (j - i <= 2 || dp[i + 1][j - 1]))
                    dp[i][j] = true;
            }
        }
        dfs(charArray, 0, new ArrayList<>());
        String[][] ans = new String[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = new String[res.get(i).size()];
            for (int j = 0; j < ans[i].length; j++) {
                ans[i][j] = res.get(i).get(j);
            }
        }
        return ans;
    }

    private void dfs(char[] charArray, int index, ArrayList<String> path) {
        if (index == charArray.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < charArray.length; i++) {
            if (dp[index][i]) {
                path.add(new String(charArray, index, i - index + 1));
                dfs(charArray, i + 1, path);
                path.remove(path.size() - 1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
