package leetcode.editor.cn;
//假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限
//，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。 
//
// 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。 
//
// 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。 
//
// 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。 
//
// 答案保证在 32 位有符号整数范围内。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
//输出：4
//解释：
//由于你的初始资本为 0，你仅可以从 0 号项目开始。
//在完成后，你将获得 1 的利润，你的总资本将变为 1。
//此时你可以选择开始 1 号或 2 号项目。
//由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
//因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。

// 示例 2：
//输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
//输出：6

// 提示： 

// 1 <= k <= 10⁵ 
// 0 <= w <= 10⁹ 
// n == profits.length 
// n == capital.length 
// 1 <= n <= 10⁵ 
// 0 <= profits[i] <= 10⁴ 
// 0 <= capital[i] <= 10⁹ 
// 
// Related Topics 贪心 数组 排序 堆（优先队列） 👍 154 👎 0


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static leetcode.editor.utils.ArrayUtils.printIntArray2D;

//leetcode submit region begin(Prohibit modification and deletion)
class findMaximizedCapitalSolution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        boolean speedUp = true;
        for (int cap : capital) {
            if (cap > w) {
                speedUp = false;
                break;
            }
        }

        if (speedUp) {
            PriorityQueue<Integer> queue = new PriorityQueue<>(k, (x, y) -> y - x);
            for (int pro : profits) {
                queue.offer(pro);
            }
            for (int i = 0; i < k; i++)
                w += queue.poll();
            return w;
        }

        // 正常分支  ---逐个消费，从利润最大的项目开始，已开展过的项目，投资额要更新为最大整数，避免重复投资
        int loopPrj = Math.min(k, profits.length);
        for (int i = 0; i < loopPrj; i++) {
            int idx = -1;
            for (int j = 0; j < profits.length; j++) {
                if (w >= capital[j]) {
                    if (idx == -1)
                        idx = j;
                    else if (profits[idx] < profits[j])
                        idx = j;

                }
            }

            if (idx == -1)
                break;

            w += profits[idx];
            capital[idx] = Integer.MAX_VALUE;
        }

        return w;
    }

    public static void main(String[] args) {
        findMaximizedCapitalSolution solution = new findMaximizedCapitalSolution();
        int res = solution.findMaximizedCapital(
                3,
                2,
                new int[]{1, 3, 6, 4, 1},
                new int[]{0, 2, 1, 8, 9});
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
