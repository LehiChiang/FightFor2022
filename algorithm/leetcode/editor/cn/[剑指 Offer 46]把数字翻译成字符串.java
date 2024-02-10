package leetcode.editor.cn;

//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，
// ……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
//
// 示例 1:
// 输入: 12258
// 输出: 5
// 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
//
// 提示：
// 0 <= num < 2³¹
// Related Topics 字符串 动态规划 👍 343 👎 0


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class translateNumSolution {
    public static void main(String[] args) {
        translateNumSolution solution = new translateNumSolution();
        System.out.println(solution.translateNum(12258));
    }

    private int translateNum(int num) {
        if (num < 10)
            return 1;
        String nums = String.valueOf(num);
        int[] dp = new int[nums.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < nums.length(); i++) {
            if (nums.charAt(i - 1) != '0' && (nums.charAt(i - 1) - '0') * 10 + (nums.charAt(i) - '0') < 26) {
                dp[i + 1] = dp[i] + dp[i - 1];
            } else
                dp[i + 1] = dp[i];
        }
        return dp[nums.length()];
    }

//    public int translateNum(int num) {
//        if (num < 10)
//            return 1;
//        String nums = String.valueOf(num);
//        int dp_0 = 1, dp_1 = 1, res = 0;
//        for (int i = 2; i <= nums.length(); i++) {
//            if (nums.charAt(i - 2) != '0' && ((nums.charAt(i - 2) - '0') * 10 + nums.charAt(i - 1) - '0') < 26)
//                res = dp_0 + dp_1;
//            else
//                res = dp_1;
//            dp_0 = dp_1;
//            dp_1 = res;
//        }
//        return res;
//    }

    private int self(int num) {
        if (num < 10)
            return 1;
        Deque<Integer> deque = new LinkedList<>();
        while (num > 0) {
            deque.push(num % 10);
            num /= 10;
        }
        int[] nums = new int[deque.size()];
        for (int i = 0; i < nums.length; i++)
            nums[i] = deque.pop();
        int dp_0 = 1, dp_1 = 1, res = 0;
        for (int i = 2; i <= nums.length; i++) {
            if (nums[i - 2] != 0 && nums[i - 2] * 10 + nums[i - 1] < 26)
                res = dp_0 + dp_1;
            else
                res = dp_1;
            dp_0 = dp_1;
            dp_1 = res;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
