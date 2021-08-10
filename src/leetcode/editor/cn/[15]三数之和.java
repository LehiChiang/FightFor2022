package leetcode.editor.cn;
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
// 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
// 注意：答案中不可以包含重复的三元组。
// 示例 1：
//输入：nums = [-1,0,1,2,-1,-4]   0, -1, -1, 1, 2, 4
//输出：[[-1,-1,2],[-1,0,1]]
// 示例 2：
//输入：nums = []
//输出：[]
// 示例 3：
//输入：nums = [0]
//输出：[]
// 提示：
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105
// Related Topics 数组 双指针 排序 
// 👍 3582 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class threeSumSolution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int third = n - 1;
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (j < third && nums[i] + nums[j] + nums[third] > 0)
                    third--;
                if (j == third)
                    break;
                if (nums[i] + nums[j] + nums[third] == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[third]);
                    res.add(list);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        threeSumSolution solution = new threeSumSolution();
        List<List<Integer>> res = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
