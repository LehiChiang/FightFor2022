package leetcode.editor.cn;
//一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
// 示例 1：
// 输入：nums = [4,1,4,6]
//输出：[1,6] 或 [6,1]
// 示例 2：
// 输入：nums = [1,2,10,4,1,4,3,3]
//输出：[2,10] 或 [10,2]
// 限制：
// 2 <= nums.length <= 10000
// Related Topics 位运算 数组 
// 👍 441 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class singleNumbersSolution {
    public static void main(String[] args) {
        singleNumbersSolution solution = new singleNumbersSolution();
        int[] res = solution.singleNumbers(new int[]{1, 2, 10, 4, 1, 4, 3, 3});
    }

    public int[] singleNumbers(int[] nums) {
        int xorAll = 0;
        int resA = 0, resB = 0;
        for (int num : nums) {
            xorAll ^= num;
        }
        int mask = 1;
        while ((mask & xorAll) == 0)
            mask <<= 1;
        for (int num : nums) {
            if ((num & mask) == 0)
                resA ^= num;
            else
                resB ^= num;
        }
        return new int[]{resA, resB};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
