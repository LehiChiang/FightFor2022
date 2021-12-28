package codetop;
//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。
// 示例 1：
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 示例 2：
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 示例 3：
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 提示：
// 1 <= nums.length <= 2500 
// -10⁴ <= nums[i] <= 10⁴
// 进阶：
// 你可以设计时间复杂度为 O(n²) 的解决方案吗？ 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
// Related Topics 数组 二分查找 动态规划 👍 1887 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class lengthOfLISSolution {

    private List<Integer> list;
    public int lengthOfLIS(int[] nums) {
       list = new ArrayList<>();
       list.add(nums[0]);
       for (int i = 1; i < nums.length; i++) {
           if (nums[i] > list.get(list.size() - 1)) {
               list.add(nums[i]);
           } else {
               int index = binarysearch(list, nums[i]);
               list.set(index, nums[i]);
           }
       }
       return list.size();
    }

    private int binarysearch(List<Integer> list, int num) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > num) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        lengthOfLISSolution solution = new lengthOfLISSolution();
        System.out.println(solution.lengthOfLIS(new int[]{2,3,7,9,4}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
