package common.sort;

/**
 * 快速排序算法实现
 */
public class QuickSort extends Sort {

    @Override
    public void sort(int[] list) {
        super.sort(list);
        quicksort(list, 0, list.length - 1);
    }

    private void quicksort(int[] nums, int start, int end) {
        if (start >= end)
            return;
        int index = partition(nums, start, end);
        quicksort(nums, start, index - 1);
        quicksort(nums, index + 1, end);
    }

//    private int partition(int[] nums, int start, int end) {
//        int pivot = nums[start];
//        while (start < end) {
//            while (start < end && nums[end] >= pivot) end--;
//            nums[start] = nums[end];
//            while (start < end && nums[start] <= pivot) start++;
//            nums[end] = nums[start];
//        }
//        nums[end] = pivot;
//        return end;
//    }

    private int partition(int[] nums, int start, int end) {
        int i = start - 1, j = start, r = nums[end];
        for (; j < end; j++) {
            if (nums[j] <= r) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, ++i, j);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
