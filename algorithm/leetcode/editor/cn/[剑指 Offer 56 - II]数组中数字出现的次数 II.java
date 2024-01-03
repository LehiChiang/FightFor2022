package leetcode.editor.cn;//在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
//
// 
//
// 示例 1： 
//
// 输入：nums = [3,4,3,3]
//输出：4
// 
//
// 示例 2： 
//
// 输入：nums = [9,1,7,9,7,9,7]
//输出：1 
//
// 
//
// 限制： 
//
// 
// 1 <= nums.length <= 10000 
// 1 <= nums[i] < 2^31 
// 
//
// 
// Related Topics 位运算 数组 👍 263 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class singleNumber3Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        int[] digits = new int[32];
        for (int num : nums) {
            int mask = 1;
            for (int i = 31; i >= 0; --i) {
                int bit = num & mask;
                if (bit != 0)
                    digits[i] += 1;
                mask <<= 1;
            }
        }
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res += digits[i] % 3 == 0 ? 0 : 1;
        }
        return res;
    }

    public static void main(String[] args) {
        singleNumber3Solution solution = new singleNumber3Solution();
        System.out.println(solution.singleNumber(new int[]{9,1,7,9,7,9,7}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
