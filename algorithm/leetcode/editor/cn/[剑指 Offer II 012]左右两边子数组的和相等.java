package leetcode.editor.cn;


//leetcode submit region begin(Prohibit modification and deletion)
class pivotIndexOffer2Solution {
    public static void main(String[] args) {
        pivotIndexOffer2Solution solution = new pivotIndexOffer2Solution();
        System.out.println(solution.pivotIndex(new int[]{1, 2, 3}));
    }

    public int pivotIndex(int[] nums) {
        int preSum = 0;
        for (int num : nums)
            preSum += num;
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == preSum - nums[i] - leftSum)
                return i;
            leftSum += nums[i];
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
