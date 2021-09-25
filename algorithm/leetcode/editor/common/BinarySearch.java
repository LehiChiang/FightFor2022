package leetcode.editor.common;

/**
 * @author chestnut
 * @creat 2021-08-26 11:37
 */
public class BinarySearch {

    // 二分搜索的左边界
    public int left_bound(int nums[], int target) {
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

    // 二分搜索的右边界
    public int right_bound(int nums[], int target) {
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

    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        System.out.println(search.right_bound(new int[]{1,2,3,3,3,3,4}, 3));
    }
}
