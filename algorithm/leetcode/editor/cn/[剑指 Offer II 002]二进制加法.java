package leetcode.editor.cn;

//给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 
//输入: a = "11", b = "10"
//输出: "101" 
//
// 示例 2: 
//
// 
//输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
//
// 
//
// 注意：本题与主站 67 题相同：https://leetcode-cn.com/problems/add-binary/ 
// Related Topics 位运算 数学 字符串 模拟 👍 13 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class addBinarySolution {
    public static void main(String[] args) {
        addBinarySolution solution = new addBinarySolution();
        System.out.println(solution.addBinary("1010", "1011"));
    }

    public String addBinary(String a, String b) {
        int numA = Integer.parseInt(a, 2), numB = Integer.parseInt(b, 2);
        int carry = 0;
        while (numB != 0) {
            carry = (numA & numB) << 1;
            numA ^= numB;
            numB = carry;
        }
        return Integer.toBinaryString(numA);
    }

    private String mock(String a, String b) {
        if (a.equals("0") || b.equals("0"))
            return a.equals("0") ? b : a;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int n = Math.max(a.length(), b.length());
        for (int i = 0; i < n; i++) {
            carry += a.length() <= i ? 0 : a.charAt(a.length() - 1 - i) - '0';
            carry += b.length() <= i ? 0 : b.charAt(b.length() - 1 - i) - '0';
            sb.append(carry % 2);
            carry /= 2;
        }
        if (carry == 1)
            sb.append(1);
        return sb.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
