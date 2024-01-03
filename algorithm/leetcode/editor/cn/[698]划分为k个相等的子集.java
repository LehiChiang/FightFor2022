package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class canPartitionKSubsetsSolution {

    private boolean[] visited;
    private int subsetSum;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0)
            return false;
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        subsetSum = sum / k;
        return backtracking(nums, k, 0, 0);
    }

    private boolean backtracking(int[] nums, int k, int curSubsetSum, int curIndex) {
        if (k == 1)
            return true;
        if (curSubsetSum == subsetSum)
            return backtracking(nums,k - 1, 0, 0);
        for (int i = curIndex; i < nums.length; i++) {
            if (!visited[i] && curSubsetSum + nums[i] <= subsetSum) {
                visited[i] = true;
                if (backtracking(nums, k, curSubsetSum + nums[i], i + 1))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        canPartitionKSubsetsSolution solution = new canPartitionKSubsetsSolution();
        System.out.println(solution.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
