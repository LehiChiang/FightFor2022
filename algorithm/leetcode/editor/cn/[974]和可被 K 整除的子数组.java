package leetcode.editor.cn;//给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
//
// 
//
// 示例： 
//
// 输入：A = [4,5,0,-2,-3,1], K = 5
//输出：7
//解释：
//有 7 个子数组满足其元素之和可被 K = 5 整除：
//[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 30000 
// -10000 <= A[i] <= 10000 
// 2 <= K <= 10000 
// 
// Related Topics 数组 哈希表 前缀和 👍 328 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class subarraysDivByKSolution {
    public int subarraysDivByK(int[] nums, int k) {
        int res = 0, preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            int key = (preSum % k + k ) % k;
            if (map.containsKey(key)) {
                res += map.get(key);
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        subarraysDivByKSolution solution = new subarraysDivByKSolution();
        System.out.println(solution.subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
