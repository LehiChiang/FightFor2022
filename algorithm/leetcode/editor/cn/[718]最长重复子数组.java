package leetcode.editor.cn;
//给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
// 示例：
// 输入：
//A: [1,2,3,2,1]
//B: [3,2,1,4,7]
//输出：3
//解释：
//长度最长的公共子数组是 [3, 2, 1] 。
// 提示：
// 1 <= len(A), len(B) <= 1000 
// 0 <= A[i], B[i] < 100
// Related Topics 数组 二分查找 动态规划 滑动窗口 哈希函数 滚动哈希 👍 576 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class findLengthSolution {
    
//    public int findLength(int[] nums1, int[] nums2) {
//        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
//        int maxLen = 0;
//        for (int i = 1; i < dp.length; i++) {
//            for (int j = 1; j < dp[i].length; j++) {
//                if (nums1[i - 1] == nums2[j - 1]) {
//                    dp[i][j] = dp[i - 1][j - 1] + 1;
//                    maxLen = Math.max(maxLen, dp[i][j]);
//                }
//            }
//        }
//        return maxLen;
//    }

    public int findLength(int[] nums1, int[] nums2) {
        return nums1.length > nums2.length ? findMax(nums2, nums1) : findMax(nums1, nums2);
    }

    private int findMax(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int res = 0;
        for (int i = 1; i <= len1; i++) {
            res = Math.max(res, maxLen(nums1, 0, nums2, len2 - i, i));
        }
        for (int i = len2 - len1; i >= 0; i--) {
            res = Math.max(res, maxLen(nums1, 0, nums2, i, len1));
        }
        for (int i = 0; i < len1; i++) {
            res = Math.max(res, maxLen(nums1, i, nums2, 0, len1 - i));
        }
        return res;
    }

    private int maxLen(int[] nums1, int i, int[] nums2, int j, int len) {
        int max = 0, count = 0;
        for (int k = 0; k < len; k++) {
            if (nums1[i + k] == nums2[j + k]) {
                count++;
            } else if (count > 0) {
                count = 0;
            }
            max = Math.max(max, count);
        }
        return max;
    }

    public static void main(String[] args) {
        findLengthSolution solution = new findLengthSolution();
        System.out.println(solution.findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
