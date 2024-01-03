package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class removeInvalidParenthesesSolution {

    private List<String> res;
    public List<String> removeInvalidParentheses(String s) {
        res = new ArrayList<>();
        int leftRemove = 0, rightRemove = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                leftRemove++;
            else if (c == ')') {
                if (leftRemove == 0)
                    rightRemove++;
                else
                    leftRemove--;
            }
        }
        dfs(s, 0, leftRemove, rightRemove);
        return res;
    }

    private void dfs(String s, int index, int leftRemove, int rightRemove) {
        if (leftRemove == 0 && rightRemove == 0) {
            if (isValidParentheses(s))
                res.add(s);
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (index != i && s.charAt(i) == s.charAt(i - 1))
                continue;
            if (leftRemove + rightRemove > s.length() - i) {
                return;
            }
            if (s.charAt(i) == '(' && leftRemove > 0)
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftRemove - 1, rightRemove);
            if (s.charAt(i) == ')' && rightRemove > 0)
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftRemove, rightRemove - 1);
        }
    }

    // 判断括号是否合法
    private boolean isValidParentheses(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                count++;
            else if (c == ')') {
                count--;
                if (count < 0)
                    return false;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        removeInvalidParenthesesSolution solution = new removeInvalidParenthesesSolution();
        System.out.println(solution.removeInvalidParentheses("()())()"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
