package leetcode.editor.cn;

//给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
// 示例 1：
//输入：a = 1, b = 2
//输出：3
//
// 示例 2：
//输入：a = 2, b = 3
//输出：5
//
// 提示：
// -1000 <= a, b <= 1000
// Related Topics 位运算 数学 👍 449 👎 0

//  2 + 3  =   5
//  0010  0011   00001  100

//leetcode submit region begin(Prohibit modification and deletion)
class getSumSolution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        getSumSolution solution = new getSumSolution();
        System.out.println(solution.getSum(52, 13));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
