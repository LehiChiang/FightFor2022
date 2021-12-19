package codetop;
//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
// 示例 1：
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 示例 2：
//输入：nums = [1]
//输出：1
// 示例 3：
//输入：nums = [0]
//输出：0
// 示例 4：
//输入：nums = [-1]
//输出：-1
// 示例 5：
//输入：nums = [-100000]
//输出：-100000
// 提示：
// 1 <= nums.length <= 3 * 10⁴ 
// -10⁵ <= nums[i] <= 10⁵
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治 动态规划 👍 3643 👎 0
//-2,1,-3,4,-1,2,1,-5,4
//-2 1 -2 4 3  5 6 1  5


//leetcode submit region begin(Prohibit modification and deletion)
class maxSubArraySolution {
    public int maxSubArray(int[] nums) {
        int preSum = 0, res = nums[0];
        for (int num : nums) {
            preSum = Math.max(preSum + num, num);
            res = Math.max(preSum, res);
        }
        return res;
    }

    public void maxSubArrayNum(int[] nums) {
        int preSum = 0, res = nums[0], maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum = Math.max(preSum + nums[i], nums[i]);
            if (preSum > res) {
                res = preSum;
                maxIndex = i;
            }
        }
        int endIndex = maxIndex;
        while (res > 0) res -= nums[maxIndex--];
        for (int i = maxIndex + 1; i <= endIndex; i++)
            System.out.print(nums[i] + " ");
    }

    public static void main(String[] args) {
        maxSubArraySolution solution = new maxSubArraySolution();
        solution.maxSubArrayNum(new int[]{6,-9,1,4,7,1,-5,-2,0,4,-3});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
