package leetcode.editor.cn;
//统计所有小于非负整数 n 的质数的数量。
// 示例 1：
// 输入：n = 10
//输出：4
//解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
// 示例 2：
// 输入：n = 0
//输出：0
// 示例 3：
// 输入：n = 1
//输出：0
// 提示：
// 0 <= n <= 5 * 106
// Related Topics 数组 数学 枚举 数论 
// 👍 718 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class countPrimesSolution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2 ; i * i < n ; i++) {
            if (isPrime[i]){
                for (int j = i * i ; j < n ; j += i)
                    isPrime[j] = false;
            }
        }

        int res = 0;
        for (int i = 2 ; i < n ; i++) {
            if (isPrime[i]) res++;
        }
        return res;
    }

    public static void main(String[] args) {
        countPrimesSolution solution = new countPrimesSolution();
        System.out.println(solution.countPrimes(1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
