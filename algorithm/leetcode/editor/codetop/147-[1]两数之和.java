package leetcode.editor.codetop;

import java.util.HashMap;

import static leetcode.editor.utils.ArrayUtils.printIntArray;

/**
 * @author chestnut
 * @creat 2021-08-25 22:30
 */
class twoSumSolution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i]))
                return new int[]{hashMap.get(target - nums[i]), i};
            hashMap.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        twoSumSolution solution = new twoSumSolution();
        printIntArray(solution.twoSum(new int[]{3,2,4}, 6));
    }
}
