package common.sort;

/**
 * 冒泡排序算法实现
 */
public class BubbleSort extends Sort{

    @Override
    public void sort(int[] nums) {
        super.sort(nums);
        boolean needNextPass = true;
        int len = nums.length;
        for (int k = 1; k < len; k++) {
            needNextPass = false;
            for (int i = 0; i < len - k; i++) {
                if (nums[i] > nums[i + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                    needNextPass = true;
                }
            }
        }
    }
}
