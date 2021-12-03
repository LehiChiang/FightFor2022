package common.sort;

import java.util.Arrays;

/**
 * 归并排序算法实现
 */
public class MergeSort extends Sort{

    @Override
    public void sort(int[] list) {
        super.sort(list);
        mergeSort(list, 0, list.length - 1);
    }

    public void mergeSort(int[] nums, int left, int right) {
        if (left >= right)
            return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] leftPart = Arrays.copyOfRange(nums, left, mid + 1);
        int[] rightPart = Arrays.copyOfRange(nums, mid + 1, right + 1);
        int numsP = left, leftP = 0, rightP = 0;
        while (leftP < leftPart.length && rightP < rightPart.length) {
            if (leftPart[leftP] < rightPart[rightP])
                nums[numsP++] = leftPart[leftP++];
            else
                nums[numsP++] = rightPart[rightP++];
        }
        while (leftP < leftPart.length)
            nums[numsP++] = leftPart[leftP++];
        while (rightP < rightPart.length)
            nums[numsP++] = rightPart[rightP++];
    }
}
