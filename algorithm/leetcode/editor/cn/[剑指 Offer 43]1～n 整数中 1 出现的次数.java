package leetcode.editor.cn;


//
// 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：6 
//
// 
//
// 限制： 
//
// 
// 1 <= n < 2^31 
// 
//
// 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/ 
// Related Topics 递归 数学 动态规划 👍 260 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class countDigitOneOfferSolution {
    public static void main(String[] args) {
        countDigitOneOfferSolution solution = new countDigitOneOfferSolution();
        System.out.println(solution.countDigitOne(6));
    }

    public int countDigitOne(int n) {
        int low = 0, digit = 1, res = 0;
        int cur = n % 10, high = n / 10;
        while (high != 0 || cur != 0) {
            if (cur == 0) res += high * digit;
            else if (cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low = cur * digit + low;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
