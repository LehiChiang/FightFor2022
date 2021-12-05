package common.binarysearch;

/**
 * @author chestnut
 */
public class BinarySearch {

    /**
     * 二分搜索
     * @param nums 二分搜索的数组
     * @param target 待搜索的目标值
     * @return 返回数组中最出现的目标值数字的索引，否则返回-1
     */
    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }
        return -1;
    }

    /**
     * 二分搜索左边界
     * @param nums 二分搜索的数组
     * @param target 搜索目标值
     * @return 返回数组中最左边出现的目标值数字的索引
     */
    public static int left_bound(int[] nums, int target) {
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
        return left < nums.length && nums[left] == target ? left : -1;
    }

    /**
     * 二分搜索的右边界
     * @param nums 二分搜索的数组
     * @param target 搜索目标值
     * @return 返回数组中最右边出现的目标值数字的索引
     */
    public static int right_bound(int[] nums, int target) {
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
        return right > 0 && nums[right - 1] == target ? right - 1 : -1;
    }
}
