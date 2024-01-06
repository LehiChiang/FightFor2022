package out.bd;

import java.util.*;

public class OutBDArray {

    private final int[] nums;

    public OutBDArray(int[] nums) {
        this.nums = nums;
    }

    /**
     * 快速选择算法，在给定的Nums中找到第K大的数
     *
     * @param k 第K个
     * @return 返回第K大的数
     */
    public int QuickSelect(int k) {
        // if k is greater than the length of nums, then return -1
        if (k > nums.length || k <= 0)
            return -1;
        return getKthNums(nums.length - k, 0, nums.length - 1);
    }

    private int getKthNums(int k, int start, int end) {
        int pivotPos = partition(start, end);
        if (pivotPos == k)
            return nums[pivotPos];
        else if (pivotPos < k)
            return getKthNums(k, pivotPos + 1, end);
        else
            return getKthNums(k, start, pivotPos - 1);
    }

    private int partition(int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) right--;
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) left++;
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 快速排序
     */
    public void QuickSort() {
        quickSort(0, nums.length - 1);
    }

    private void quickSort(int left, int right) {
        if (left >= right)
            return;
        int pivotPos = partition(left, right);
        quickSort(left, pivotPos - 1);
        quickSort(pivotPos + 1, right);
    }

    /**
     * 和可被K整除的子数组个数
     *
     * @param k
     * @return 子数组个数
     */
    public int SubarraysDivByK(int k) {
        int res = 0, preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            preSum += num;
            int key = Math.floorMod(preSum, k);
            if (map.containsKey(key)) {
                res += map.get(key);
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return res;
    }

    /**
     * 最长递增子序列（动态规划版）
     * Length Of Longest Increasing Subsequence
     *
     * @return 子序列的长度
     */
    public int LIS() {
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);

                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 最长递增子序列（贪心+二分搜索）
     * Length Of Longest Increasing Subsequence With Greedy Algorithm
     *
     * @return 子序列的长度
     */
    public int LISWithGA() {
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > list.get(list.size() - 1)) {
                list.add(nums[i]);
            } else {
                int index = ApproximateBinarySearch(nums[i]);
                list.set(index, nums[i]);
            }
        }
        return list.size();
    }

    /**
     * 二分搜索（精确搜索）
     *
     * @param target 被搜索的数字
     * @return 返回target的下标，不存在返回-1
     */
    public int AccurateBinarySearch(int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        return -1;
    }

    /**
     * 二分搜索（模糊搜索）
     *
     * @param target 被搜索的数字
     * @return 返回target的下标，不存在返回应该出现target的下标
     */
    public int ApproximateBinarySearch(int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    /**
     * 最长连续不重复子串 LengthOfLongestContinuousNoRepeatSubstring
     *
     * @param s 字符串
     * @return 长度
     */
    public int LCNRS(String s) {
        Set<Character> set = new HashSet<>();
        int length = s.length();
        int res = 0, end = -1;
        for (int start = 0; start < length; start++) {
            if (end + 1 >= length)
                break;
            if (start != 0)
                set.remove(s.charAt(start - 1));
            while (end + 1 < length && !set.contains(s.charAt(end + 1))) {
                set.add(s.charAt(end + 1));
                end++;
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}
