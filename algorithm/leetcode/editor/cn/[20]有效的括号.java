package leetcode.editor.cn;
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
// 有效字符串需满足：
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。
// 示例 1：
//输入：s = "()"
//输出：true
// 示例 2：
//输入：s = "()[]{}"
//输出：true
// 示例 3：
//输入：s = "(]"
//输出：false
// 示例 4：
//输入：s = "([)]"
//输出：false
// 示例 5：
//输入：s = "{[]}"
//输出：true
// 提示：
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成
// Related Topics 栈 字符串 
// 👍 2514 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class isValidCommaSolution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') stack.push(')');
            else if (ch == '[') stack.push(']');
            else if (ch == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != ch) return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        isValidCommaSolution solution = new isValidCommaSolution();
        System.out.println(solution.isValid("]]"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
