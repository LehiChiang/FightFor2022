package common.binarysearch;

/**
 * @author chestnut
 */
public class BinarySearch {

    /**
     * 二分搜索左边界
     * @param nums 二分搜索的数组
     * @param target 搜索目标值
     * @return 返回数组中最左边出现的目标值数字的索引
     */
    public static int left_bound(int nums[], int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                right = mid;
            else if (nums[mid] > target)
                right = mid;
            else if (nums[mid] < target)
                left = mid + 1;
        }
        return left;
    }

    /**
     * 二分搜索的右边界
     * @param nums 二分搜索的数组
     * @param target 搜索目标值
     * @return 返回数组中最右边出现的目标值数字的索引
     */
    public static int right_bound(int nums[], int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                left = mid + 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        return left - 1;
    }
}
