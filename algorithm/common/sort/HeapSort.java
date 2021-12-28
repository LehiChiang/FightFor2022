package common.sort;

import java.util.Arrays;

/**
 * 堆排序算法实现
 */
public class HeapSort extends Sort{

    @Override
    public void sort(int[] list) {
        super.sort(list);
        int headSize = list.length;
        buildMinHeap(list, headSize);
        for (int i = list.length - 1; i >= 0; --i) {
            System.out.print(list[0] + ", ");
            swap(list, 0, i);
            minHeapify(list, 0, --headSize);
        }
    }

    private void minHeapify(int[] nums, int index, int headSize) {
        int left = 2 * index + 1, right = 2 * index + 2, minIndex = index;
        if (left < headSize && nums[left] < nums[minIndex])
            minIndex = left;
        if (right < headSize && nums[right] < nums[minIndex])
            minIndex = right;
        if (minIndex != index) {
            swap(nums, index, minIndex);
            minHeapify(nums, minIndex, headSize);
        }
    }

    private void buildMinHeap(int[] nums, int heapSize) {
        for (int i = nums.length / 2; i >= 0; i--) {
            minHeapify(nums, i, heapSize);
        }
    }

    private void swap(int[] nums, int left, int index) {
        int temp = nums[left];
        nums[left] = nums[index];
        nums[index] = temp;
    }
}
