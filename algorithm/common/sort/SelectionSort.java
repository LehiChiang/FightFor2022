package common.sort;

/**
 * 简单选择排序算法实现
 */
public class SelectionSort extends Sort {
    @Override
    public void sort(int[] nums) {
        super.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int min_index = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[min_index])
                    min_index = j;
            }
            int temp = nums[i];
            nums[i] = nums[min_index];
            nums[min_index] = temp;
        }
    }
}
