package leetcode.editor.cn;

//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
// 示例 1：
// 输入：target = 9
//输出：[[2,3,4],[4,5]]

// 示例 2：
// 输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]

// 限制：
// 1 <= target <= 10^5
// Related Topics 数学 双指针 枚举 👍 357 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class findContinuousSequenceSolution {
    public static void main(String[] args) {
        findContinuousSequenceSolution solution = new findContinuousSequenceSolution();
        System.out.println(Arrays.deepToString(solution.findContinuousSequence(9)));
    }

    public int[][] findContinuousSequence(int target) {
        List<int[]> res = new ArrayList<>();
        int sum = 0;
        // i为右边界，j为左边界
        for (int i = 1, j = 1; i <= (target + 1) / 2 + 1; ) {
            if (sum == target) {
                int[] array = new int[i - j];
                for (int k = j; k < i; k++)
                    array[k - j] = k;
                res.add(array);
                sum -= j;
                j++;
            } else if (sum > target) {
                sum -= j;
                j++;
            } else {
                sum += i;
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
