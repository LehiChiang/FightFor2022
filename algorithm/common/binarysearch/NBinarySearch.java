package common.binarysearch;

import org.jetbrains.annotations.NotNull;

public class NBinarySearch {
    public int search(int @NotNull [] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        return -1;
    }
}
