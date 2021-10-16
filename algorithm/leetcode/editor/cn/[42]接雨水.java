package leetcode.editor.cn;

//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
// 示例 1：
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
// 示例 2：
//输入：height = [4,2,0,3,2,5]
//输出：9
// 提示：
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 2758 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class trapSolution {
//    public int trap(int[] height) {
//        int n = height.length;
//        int res = 0;
//        int[] l_max = new int[n];
//        int[] r_max = new int[n];
//        l_max[0] = height[0];
//        r_max[n - 1] = height[n - 1];
//        for (int i = 1; i < n; i++)
//            l_max[i] = Math.max(height[i], l_max[i - 1]);
//        for (int j = n - 2; j >= 0; j--)
//            r_max[j] = Math.max(height[j], r_max[j + 1]);
//        for (int i = 1; i < n - 1; i++)
//            res += Math.min(l_max[i], r_max[i]) - height[i];
//        return res;
//    }

    public int trap(int[] height) {
        int n = height.length;
        int l_max = height[0], r_max = height[n - 1];
        int left = 0, right = n - 1, res = 0;
        while (left <= right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            if (l_max < r_max) {
                res += l_max - height[left];
                left++;
            } else {
                res += r_max - height[right];
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        trapSolution solution = new trapSolution();
        System.out.println(solution.trap(new int[]{0, 1, 0, 2, 1,0,1,3,2,1,2,1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
