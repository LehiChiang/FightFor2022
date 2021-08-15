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

//    public List<List<Integer>> threeSum(int[] nums) {
//        Arrays.sort(nums);
//        return nSumTarget(nums, 3, 0, 0);
//    }
//
//    private List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
//        int numsLen = nums.length;
//        List<List<Integer>> res = new ArrayList<>();
//        if (n < 2 || numsLen < n)
//            return res;
//        if (n == 2) {
//            int low = start, high = numsLen - 1;
//            while (low < high) {
//                int sum = nums[low] + nums[high];
//                int left = nums[low], right = nums[high];
//                if (sum == target) {
//                    ArrayList<Integer> temp = new ArrayList<>();
//                    temp.add(nums[low]);
//                    temp.add(nums[high]);
//                    res.add(temp);
//                    while (low < high && nums[low] == left) low++;
//                    while (low < high && nums[high] == right) high--;
//                } else if (sum < target) {
//                    while (low < high && nums[low] == left) low++;
//                } else while (low < high && nums[high] == right) high--;
//            }
//        } else {
//            for (int i = start; i < numsLen; i++) {
//                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
//                for (List<Integer> list : sub) {
//                    list.add(nums[i]);
//                    res.add(list);
//                }
//                // 控制在遍历排完序数组的时候防止相邻的重复元素使用
//                while (i < numsLen - 1 && nums[i] == nums[i + 1]) i++;
//            }
//        }
//        return res;
//    }

    public static void main(String[] args) {
        threeSumSolution solution = new threeSumSolution();
        List<List<Integer>> res = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
