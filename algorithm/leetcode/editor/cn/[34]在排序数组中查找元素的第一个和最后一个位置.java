package leetcode.editor.cn;//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 二分查找 👍 1324 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class searchRangeSolution {
    public int[] searchRange(int[] nums, int target) {
        int leftBound, rightBound;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        leftBound = left < nums.length && nums[left] == target ? left : -1 ;
        left = 0;
        right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        rightBound = right > 0 && nums[right - 1] == target ? right - 1 : -1;
        return new int[]{leftBound, rightBound};
    }

    public static void main(String[] args) {
        searchRangeSolution solution = new searchRangeSolution();
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5,7,7,8,8,10}, 100)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
