package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class findUnsortedSubarraySolution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;
        int max = nums[0], min = nums[n - 1];
        for (int i = 0; i < n ;i++) {
            if (nums[i] >= max)
                max = nums[i];
            else left = i;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] <= min)
                min = nums[i];
            else right = i;
        }
        // 数字本身就是有序的情况，直接返回0；
        if (left == 0 && right == n - 1)
            return 0;
        return left - right + 1;
    }

    public static void main(String[] args) {
        findUnsortedSubarraySolution solution = new findUnsortedSubarraySolution();
        System.out.println(solution.findUnsortedSubarray(new int[]{2,6,9,13,12,11,15}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
