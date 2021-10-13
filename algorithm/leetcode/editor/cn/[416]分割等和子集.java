package leetcode.editor.cn;

//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
// 示例 1：
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。
// 示例 2：
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
//
// 提示：
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100
// Related Topics 数组 动态规划 👍 965 👎 0


import utils.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class canPartitionSolution {

//    /**
//     * 未空间压缩版
//     * @param nums
//     * @return
//     */
//    public boolean canPartition(int[] nums) {
//        int sum = 0;
//        for (int num : nums) sum += num;
//        if (sum % 2 != 0) return false;
//        sum /= 2;
//        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
//        for (int i = 0; i <= nums.length; i++)
//            dp[i][0] = true;
//        for (int i = 1; i <= nums.length; i++) {
//            for (int j = 1; j <= sum; j++) {
//                if (j - nums[i - 1] < 0) {
//                    dp[i][j] = dp[i - 1][j];
//                } else {
//                    dp[i][j] = dp[i - 1][j - nums[i - 1]] | dp[i - 1][j];
//                }
//            }
//        }
//        return dp[nums.length][sum];
//    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; j >= 1; j--) {
                if (j - nums[i - 1] >= 0) {
                    dp[j] = dp[j - nums[i - 1]] | dp[j];
                }
            }
        }
        return dp[sum];
    }

    public static void main(String[] args) {
        canPartitionSolution solution = new canPartitionSolution();
        System.out.println(solution.canPartition(new int[]{1, 5, 11, 5}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
