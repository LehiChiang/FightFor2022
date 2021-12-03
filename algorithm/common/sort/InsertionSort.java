package common.sort;

/**
 * 插入排序算法实现
 */
public class InsertionSort extends Sort {
    @Override
    public void sort(int[] nums) {
        super.sort(nums);
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int temp = nums[i];
            int j;
            for (j = i - 1; j >= 0 && nums[j] > temp; j--) {
                    nums[j + 1] = nums[j];
            }
            nums[j + 1] = temp;
        }
    }
}
