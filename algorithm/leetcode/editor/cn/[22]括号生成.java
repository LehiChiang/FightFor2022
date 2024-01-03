package leetcode.editor.cn;

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
// 有效括号组合需满足：左括号必须以正确的顺序闭合。
// 示例 1：
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 示例 2：
//输入：n = 1
//输出：["()"]
// 提示：
// 1 <= n <= 8
// Related Topics 字符串 动态规划 回溯 👍 2183 👎 0

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class generateParenthesisSolution {

    private List<String> res;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        dfs(n, new StringBuilder(), 0, 0);
        return res;
    }

    private void dfs(int n, StringBuilder path, int left, int right) {
        if (left + right == n * 2) {
            res.add(path.toString());
            return;
        }
        if (left < n) {
            path.append("(");
            dfs(n, path, left + 1, right);
            path.deleteCharAt(path.length() - 1);
        }

        if (right < left) {
            path.append(")");
            dfs(n, path, left, right + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        generateParenthesisSolution solution = new generateParenthesisSolution();
        System.out.println(solution.generateParenthesis(4));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
