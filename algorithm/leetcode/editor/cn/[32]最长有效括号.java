package leetcode.editor.cn;
//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
// 示例 1：
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 示例 2：
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 示例 3：
//输入：s = ""
//输出：0
// 提示：
// 0 <= s.length <= 3 * 10⁴ 
// s[i] 为 '(' 或 ')'
// Related Topics 栈 字符串 动态规划 👍 1568 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class longestValidParenthesesSolution {
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        longestValidParenthesesSolution solution = new longestValidParenthesesSolution();
        System.out.println(solution.longestValidParentheses(")()())"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
