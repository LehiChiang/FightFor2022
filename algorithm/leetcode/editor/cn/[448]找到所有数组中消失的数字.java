package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class findDisappearedNumbersSolution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] - 1 != i) {
                if (nums[i] == nums[nums[i] - 1])
                    break;
                swap(nums, i, nums[i] - 1);

            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i)
                res.add(i + 1);
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        findDisappearedNumbersSolution solution = new findDisappearedNumbersSolution();
        System.out.println(solution.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
