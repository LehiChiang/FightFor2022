package leetcode.editor.cn;

//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
// 示例 1：
//输入：s = "1 + 1"
//输出：2
// 示例 2：
//输入：s = " 2-1 + 2 "
//输出：3
// 示例 3：
//输入：s = "(1+(4+5+2)-3)+(6+8)"
//输出：23
// 提示：
// 1 <= s.length <= 3 * 10⁵ 
// s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 
// s 表示一个有效的表达式
// Related Topics 栈 递归 数学 字符串 👍 684 👎 0


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class calculateSolution {
    public int calculate(String s) {
        Deque<Integer> ops = new LinkedList<>();
        int res = 0, sign = 1, n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;
            else if (c >= '0' && c <= '9') {
                int num = 0;
                while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9')
                    num = num * 10 + (s.charAt(i++) - '0');
                res += sign * num;
                i--; // while循环多加了一次
            } else if (c == '+' || c == '-') { //将前面构成的数字num清零
                sign = c == '+' ? 1 : -1;
            } else if (c == '(') { // 遇到（就意味着 开始一个新的计算了，之前计算的res和sign放入栈中，新的res和sign恢复到默认值
                ops.push(res);
                ops.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') { // 遇到）就意味着结束一个计算了，将当前的结果和栈中保存的结果进行运算一次
                res *= ops.pop();
                res += ops.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        calculateSolution solution = new calculateSolution();
        System.out.println(solution.calculate("(50-3)-(499-769)"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
