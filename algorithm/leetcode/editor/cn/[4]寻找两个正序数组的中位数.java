package leetcode.editor.cn;//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
// Related Topics 数组 二分查找 分治 👍 4654 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class findMedianSortedArraysSolution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 > len2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int totalLen = len1 + len2;
        if (totalLen % 2 == 1) {
            return findKElement(nums1, 0, nums2, 0, (totalLen / 2) + 1);
        } else {
            int num1 = findKElement(nums1, 0, nums2, 0, totalLen / 2);
            int num2 = findKElement(nums1, 0, nums2, 0, (totalLen / 2) + 1);
            return (num1 + num2) / 2.0;
        }
    }

    private int findKElement(int[] nums1, int start1, int[] nums2, int start2, int k) {
        // len表示待选的数组长度
        int len1 = nums1.length - start1;
        int len2 = nums2.length - start2;
        if (len1 > len2)
            return findKElement(nums2, start2, nums1, start1, k);
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int i = start1 + Math.min(nums1.length - start1, k / 2) - 1;
        int j = start2 + Math.min(nums2.length - start2, k / 2) - 1;
        if (nums1[i] <= nums2[j]) {
            return findKElement(nums1, i + 1, nums2, start2, k - (i - start1 + 1));
        } else {
            return findKElement(nums1, start1, nums2, j + 1, k - (j - start2 + 1));
        }
    }

    private double partArray(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length;
        int n = nums2.length;
        // 分割左右两半的元素数量
        int leftSize = (m + n + 1) / 2;
        // 在[0-m]区间找一个分界线分割
        int left = 0, right = m;
        while (left < right) {
            int i = left + (right - left) / 2;
            int j = leftSize - i;
            if (nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
        }
        int i = left;
        int j = leftSize - i;
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

        if ((m + n) % 2 == 1)
            return Math.max(nums1LeftMax, nums2LeftMax);
        else
            return (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
    }

    public static void main(String[] args) {
        findMedianSortedArraysSolution solution = new findMedianSortedArraysSolution();
        int[] nums1 = {11};
        int[] nums2 = {10};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
