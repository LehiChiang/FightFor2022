package leetcode.editor.cn;//给定一个含有 n 个正整数的数组和一个正整数 target 。
//
// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长
//度。如果不存在符合条件的子数组，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：target = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 
//
// 示例 2： 
//
// 
//输入：target = 4, nums = [1,4,4]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：target = 11, nums = [1,1,1,1,1,1,1,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target <= 10⁹ 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁵ 
// 
//
// 
//
// 进阶： 
//
// 
// 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。 
// 
//
// 
//
// 注意：本题与主站 209 题相同：https://leetcode-cn.com/problems/minimum-size-subarray-sum/ 
//
// Related Topics 数组 二分查找 前缀和 滑动窗口 👍 29 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class minSubArrayLenOffer2Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int length = nums.length;
        int res = target + 1, left = 0, right = -1, sum = 0;
        while (right < length) {
            right++;
            while (sum >= target) {
                res = Math.min(res, right - left);
                sum -= nums[left++];
            }
            if (right < length)
                sum += nums[right];
        }
        return res == target + 1 ? 0 : res;
    }

    private int extracted(int target, int[] nums) {
        int len = nums.length;
        int end = -1, start = 0, res = target + 1, sum = 0;
        while (end < len - 1) {
            while (end + 1 < len && sum < target)
                sum += nums[++end];
            if (start <= end) {
                res = Math.min(res, end - start + 1);
                sum -= nums[start++];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        minSubArrayLenOffer2Solution solution = new minSubArrayLenOffer2Solution();
        System.out.println(solution.minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
