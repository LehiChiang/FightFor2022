package codetop;
//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
// 示例 1:
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 示例 2:
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4
// 提示：
// 1 <= k <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1411 👎 0


import utils.ArrayUtils;

import java.util.Arrays;
import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
class findKthLargestSolutionWithHeap {

    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            swap(nums, 0, i);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    private void maxHeapify(int[] nums, int i, int heapSize) {
        int left = 2 * i + 1, right = 2 * i + 2, maxIndex = i;
        if (left < heapSize && nums[left] > nums[maxIndex])
            maxIndex = left;
        if (right < heapSize && nums[right] > nums[maxIndex])
            maxIndex = right;
        if (maxIndex != i) {
            swap(nums, maxIndex, i);
            maxHeapify(nums, maxIndex, heapSize);
        }
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = nums.length / 2; i >= 0; --i) {
            maxHeapify(nums, i, heapSize);
        }
    }

    private void swap(int[] nums, int maxIndex, int i) {
        int temp = nums[maxIndex];
        nums[maxIndex] = nums[i];
        nums[i] = temp;
    }

    public static void main(String[] args) {
        findKthLargestSolutionWithHeap solution = new findKthLargestSolutionWithHeap();
        System.out.println(solution.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
