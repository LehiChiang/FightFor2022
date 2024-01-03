package leetcode.editor.cn;

//已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
// 不要使用系统的 Math.random() 方法。
// 示例 1:
//输入: 1
//输出: [7]
//
// 示例 2:
//输入: 2
//输出: [8,4]
//
// 示例 3:
//输入: 3
//输出: [8,1,10]
//
// 提示:
// rand7 已定义。 
// 传入参数: n 表示 rand10 的调用次数。 
//
// 进阶:
// rand7()调用次数的 期望值 是多少 ? 
// 你能否尽量少调用 rand7() ? 
// 
// Related Topics 数学 拒绝采样 概率与统计 随机化 👍 343 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Random;

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 *
 * @return a random integer in the range 1 to 7
 */
class SolBase {
    private Random random = new Random();

    public int rand7() {
        return random.nextInt(7) + 1;
    }
}

class rand10Solution extends SolBase {

    public static void main(String[] args) {
        rand10Solution solution = new rand10Solution();
        int n = 5;
        for (int i = 0; i < n; i++)
            System.out.println(solution.rand10());
    }

    /**
     * 简易版本
     *
     * @return
     */
    public int rand10Simp() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7();
            if (num <= 40) return num % 10 + 1;
        }
    }

    /**
     * 优化版本
     * 原理：尽量减少rand7()的调用次数，如果第一次生成的数字正好在拒绝采样的区间内，那么我们将其映射到其他的
     * 随机数区间上，在进行一次采样。直到映射到接近rand7()的最小倍数的区间上为止。
     *
     * @return
     */
    public int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7();
            if (num <= 40) return num % 10 + 1;

            int num1 = (num - 40 - 1) * 7 + rand7();
            if (num1 <= 60) return num1 % 10 + 1;

            int num2 = (num1 - 60 - 1) * 7 + rand7();
            if (num2 <= 20) return num2 % 10 + 1;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
