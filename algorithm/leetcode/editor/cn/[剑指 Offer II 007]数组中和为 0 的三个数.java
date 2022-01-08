package leetcode.editor.cn;//给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，使得 a + b + c = 0 ？请找出所有和为 0 且
//不重复 的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// 
//
// 注意：本题与主站 15 题相同：https://leetcode-cn.com/problems/3sum/ 
// Related Topics 数组 双指针 排序 👍 31 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class threeSumOffer2Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int third = nums.length - 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                if (nums[i] + nums[j] + nums[third] < 0)
                    continue;
                while (j < third && nums[i] + nums[j] + nums[third] > 0)
                    third--;
                if (j == third)
                    break;
                if (nums[i] + nums[j] + nums[third] == 0) {
                    List<Integer> sum = new ArrayList<>();
                    sum.add(nums[i]);
                    sum.add(nums[j]);
                    sum.add(nums[third]);
                    res.add(sum);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        threeSumOffer2Solution solution = new threeSumOffer2Solution();
        System.out.println(solution.threeSum(new int[]{-1, -1, -1, -1, -1, 2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
