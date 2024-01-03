package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class generateParenthesisOffer2Solution {

    private List<String> res;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        dfs(n, 0, 0, new StringBuilder());
        return res;
    }

    private void dfs(int n, int leftNum, int rightNum, StringBuilder path) {
        if (leftNum + rightNum == 2 * n) {
            res.add(path.toString());
            return;
        }
        if (leftNum < n) {
            path.append('(');
            dfs(n, leftNum + 1, rightNum, path);
            path.deleteCharAt(path.length() - 1);
        }
        if (rightNum < leftNum) {
            path.append(')');
            dfs(n, leftNum, rightNum + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        generateParenthesisOffer2Solution solution = new generateParenthesisOffer2Solution();
        System.out.println(solution.generateParenthesis(3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
