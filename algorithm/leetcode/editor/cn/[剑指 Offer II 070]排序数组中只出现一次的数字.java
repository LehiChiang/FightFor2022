package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class singleNonDuplicateSolution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[mid + 1]) {
                if (mid % 2 == 1) right = mid;
                else left = mid + 1;
            } else {
                if (mid % 2 == 0) right = mid;
                else left = mid + 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        singleNonDuplicateSolution solution = new singleNonDuplicateSolution();
        System.out.println(solution.singleNonDuplicate(new int[]{1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
