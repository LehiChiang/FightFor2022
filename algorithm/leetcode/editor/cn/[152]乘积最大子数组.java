package leetcode.editor.cn;

//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
// 示例 1:
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 示例 2:
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
// Related Topics 数组 动态规划 👍 1418 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class maxProductSolution {
    public int maxProduct(int[] nums) {
        int imax = nums[0], imin = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int i_max = imax, i_min = imin;
            imax = Math.max(i_min * nums[i], Math.max(i_max * nums[i], nums[i]));
            imin = Math.min(i_max * nums[i], Math.min(i_min * nums[i], nums[i]));
            max = i_max > max ? i_max : max;
        }
        return max;
    }

    public static void main(String[] args) {
        maxProductSolution solution = new maxProductSolution();
        System.out.println(solution.maxProduct(new int[]{-4,-3,-2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
