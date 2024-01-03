package leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class productExceptSelfOffer2Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++)
            res[i] = res[i - 1] * nums[i - 1];
        int num = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            res[i] = res[i] * num * nums[i + 1];
            num = num * nums[i + 1];
        }
        return res;
    }

    public static void main(String[] args) {
        productExceptSelfOffer2Solution solution = new productExceptSelfOffer2Solution();
        System.out.println(Arrays.toString(solution.productExceptSelf(new int[]{3,1,5,3,6})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
