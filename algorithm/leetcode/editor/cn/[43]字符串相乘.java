package leetcode.editor.cn;

//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
// 示例 1:
// 输入: num1 = "2", num2 = "3"
//输出: "6"
// 示例 2:
// 输入: num1 = "123", num2 = "456"
//输出: "56088"
// 说明：
// num1 和 num2 的长度小于110。 
// num1 和 num2 只包含数字 0-9。 
// num1 和 num2 均不以零开头，除非是数字 0 本身。 
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
// Related Topics 数学 字符串 模拟 👍 774 👎 0



//leetcode submit region begin(Prohibit modification and deletion)
class multiplySolution {
    public String multiply(String num1, String num2) {
        return num1.length() < num2.length() ? getMultiply(num2, num1) : getMultiply(num1, num2);
    }

    private String getMultiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        String sum = "0";
        for (int j = num2.length() - 1; j >= 0; j--){
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            for (int k = j; k < num2.length() - 1; k++)
                sb.append("0");
            for (int i = num1.length() - 1; i >= 0; i--) {
                int n1 = num1.charAt(i) - '0';
                int n2 = num2.charAt(j) - '0';
                int res = n1 * n2 + carry;
                carry = res / 10;
                sb.append(res % 10);
            }
            if (carry > 0) sb.append(carry);
            sum = getAdd(sum, sb.reverse().toString());
        }
        return sum;
    }

    private String getAdd(String num1, String num2) {
        int carry = 0;
        int n1 = num1.length() - 1, n2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (n1 >= 0 || n2 >= 0 || carry != 0) {
            int a = n1 < 0 ? 0 : num1.charAt(n1) - '0';
            int b = n2 < 0 ? 0 : num2.charAt(n2) - '0';
            int sum = a + b + carry;
            carry = sum / 10;
            sb.append(sum % 10);
            n1--;
            n2--;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        multiplySolution solution = new multiplySolution();
        System.out.println(solution.multiply("756", "987"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
