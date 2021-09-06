package leetcode.editor.codetop;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author chestnut
 * @creat 2021-08-25 11:14
 */
class findKthLargestSolution {
    // 堆法
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        for (int j = k; j < nums.length; j++) {
            if (nums[j] > queue.peek()) {
                queue.poll();
                queue.offer(nums[j]);
            }
        }
        return queue.peek();
    }

    // 快速查找法
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        int mid = _partition(nums, start, end);
        if (mid == k)
            return nums[mid];
        else
            return mid > k ? quickSelect(nums, start, mid - 1, k) : quickSelect(nums, mid + 1, end, k);
    }

    // 这个函数要反复写
    private int partition(int nums[], int start, int end) {
        int pivot = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= pivot) end--;
            nums[start] = nums[end];
            while (start < end && nums[start] <= pivot) start++;
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        return start;
    }

    // 算法导论 分区方法

    /**
     * i，j，r三个变量分别代表左半部分最后一个元素，未分区的元素的第一个元素，最后一个最为中枢的元素
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int _partition(int nums[], int start, int end) {
        int i = start - 1;
        int r = nums[end];
        for (int j = start; j <= end - 1; j++) {
            if (nums[j] <= r) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, ++i, end);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        findKthLargestSolution solution = new findKthLargestSolution();
        System.out.println(solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }
}
