package leetcode.editor.codetop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class threeSumSolutionCodeTop {
//    public List<List<Integer>> threeSum(int[] nums) {
//        int n = nums.length;
//        Arrays.sort(nums);
//        List<List<Integer>> res = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            if (i > 0 && nums[i] == nums[i - 1])
//                continue;
//            int k = n - 1;
//            for (int j = i + 1; j < n; j++) {
//                if (j > i + 1 && nums[j] == nums[j - 1])
//                    continue;
//                while (k > j && nums[k] + nums[i] + nums[j] > 0)
//                    k--;
//                if (k == j)
//                    break;
//                if (nums[k] + nums[i] + nums[j] == 0) {
//                    ArrayList<Integer> threeSum = new ArrayList<>();
//                    threeSum.add(nums[i]);
//                    threeSum.add(nums[j]);
//                    threeSum.add(nums[k]);
//                    res.add(threeSum);
//                }
//            }
//        }
//        return res;
//    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return nSum(nums, 3, 0, 0);
    }

    private List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        int numsLen = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2) {
            return res;
        } else if (n == 2) {
            int low = start, high = numsLen - 1;
            while (low < high) {
                int leftNum = nums[low], rightNum = nums[high];
                int sum = leftNum + rightNum;
                if (sum == target) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(nums[low]);
                    temp.add(nums[high]);
                    res.add(temp);
                    while (low < high && nums[low] == leftNum) low++;
                    while (low < high && nums[high] == rightNum) high--;
                } else if (sum < target) {
                    while (low < high && nums[low] == leftNum) low++;
                } else {
                    while (low < high && nums[high] == rightNum) high--;
                }
            }
        } else {
            for (int i = start; i < numsLen; i++) {
                List<List<Integer>> partSumList = nSum(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> list : partSumList) {
                    list.add(nums[i]);
                    res.add(list);
                }
                while (i < numsLen - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        threeSumSolutionCodeTop solutionCodeTop = new threeSumSolutionCodeTop();
        System.out.println(solutionCodeTop.threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}
