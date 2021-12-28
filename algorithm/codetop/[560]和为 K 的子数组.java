package codetop;
//给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3], k = 3
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -1000 <= nums[i] <= 1000 
// -10⁷ <= k <= 10⁷ 
// 
// Related Topics 数组 哈希表 前缀和 👍 1130 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class subarraySumSolution {
    public int subarraySum(int[] nums, int k) {
        // key表示前缀和，value表示前缀和的数量
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0, preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (map.containsKey(preSum - k))
                res += map.get(preSum - k);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        subarraySumSolution solution = new subarraySumSolution();
        int res = solution.subarraySum(new int[]{1, 2, 3, -3, 3}, 3);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
