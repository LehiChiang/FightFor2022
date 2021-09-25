package leetcode.editor.codetop;

import static leetcode.editor.utils.ArrayUtils.printIntArray;

/**
 * @author chestnut
 * @creat 2021-08-25 22:45
 */
class sortSolution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int mid = partition(nums, start, end);
            quickSort(nums, start, mid - 1);
            quickSort(nums, mid + 1, end);
        }
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= pivot) high--;
            nums[low] = nums[high];
            while (low < high && nums[low] <= pivot) low++;
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }

    public static void main(String[] args) {
        sortSolution solution = new sortSolution();
        printIntArray(solution.sortArray(new int[]{5,1,1,2,0,0}));
    }
}
