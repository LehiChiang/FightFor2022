package leetcode.editor.cn;
//给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
// 返回 A 的任意排列，使其相对于 B 的优势最大化。
// 示例 1：
// 输入：A = [2,7,11,15], B = [1,10,4,11]
//输出：[2,11,7,15]
// 示例 2：
// 输入：A = [12,24,8,32], B = [13,25,32,11]
//输出：[24,32,8,12]
// 提示：
// 1 <= A.length = B.length <= 10000 
// 0 <= A[i] <= 10^9 
// 0 <= B[i] <= 10^9
// Related Topics 贪心 数组 排序 👍 154 👎 0


import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class advantageCountSolution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Arrays.sort(nums1);
        PriorityQueue<int[]> queue = new PriorityQueue<>((pair1, pair2) -> pair2[1] - pair1[1]);
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, nums2[i]});
        }
        int left = 0, right = n - 1;
        int[] res = new int[n];

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int i = pair[0], maxValue = pair[1];
            if (nums1[right] > maxValue) {
                res[i] = nums1[right];
                right--;
            } else {
                res[i] = nums1[left];
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        advantageCountSolution solution = new advantageCountSolution();
        System.out.println(Arrays.toString(solution.advantageCount(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
