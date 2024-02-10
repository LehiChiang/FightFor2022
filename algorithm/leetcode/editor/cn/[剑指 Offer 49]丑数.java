package leetcode.editor.cn;

//我们把只包含质因子 f2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明:
// 1 是丑数。 
// n 不超过1690。
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 👍 257 👎 0


import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class nthUglyNumberOfferSolution {
    public static void main(String[] args) {
        nthUglyNumberOfferSolution solution = new nthUglyNumberOfferSolution();
        System.out.println(solution.nthUglyNumber(10));
    }

    public int nthUglyNumber(int n) {
        int[] factors = new int[]{2, 3, 5};
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.offer(1L);
        Set<Long> seen = new HashSet<>();
        int uglyNum = 0;
        for (int i = 0; i < n; i++) {
            long num = queue.poll();
            uglyNum = (int) num;
            for (int factor : factors) {
                if (seen.add(factor * num))
                    queue.offer(factor * num);
            }
        }
        return uglyNum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
