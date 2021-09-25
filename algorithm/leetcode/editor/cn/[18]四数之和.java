package leetcode.editor.cn;
//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
// 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] ：
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target
// 你可以按 任意顺序 返回答案 。
// 示例 1：
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 示例 2：
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 提示：
// 1 <= nums.length <= 200 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109
// Related Topics 数组 双指针 排序 
// 👍 919 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class fourSumSolution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSumTarget(nums, 4, 0, target);
    }

    private List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        int numsLen = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2 || numsLen < n)
            return res;
        if (n == 2) {
            int low = start, high = numsLen - 1;
            while (low < high) {
                int sum = nums[low] + nums[high];
                int left = nums[low], right = nums[high];
                if (sum == target) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(nums[low]);
                    temp.add(nums[high]);
                    res.add(temp);
                    while (low < high && nums[low] == left) low++;
                    while (low < high && nums[high] == right) high--;
                } else if (sum < target) {
                    while (low < high && nums[low] == left) low++;
                } else while (low < high && nums[high] == right) high--;
            }
        } else {
            for (int i = start; i < numsLen; i++) {
                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> list : sub) {
                    list.add(nums[i]);
                    res.add(list);
                }
                // 控制在遍历排完序数组的时候防止相邻的重复元素使用
                while (i < numsLen - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        fourSumSolution solution = new fourSumSolution();
        List<List<Integer>> res = solution.fourSum(new int[]{2,2,2,2,2}, 8);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
