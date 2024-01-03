package out.bd;

import java.util.HashMap;
import java.util.Map;

public class OutBDArray {

    private final int[] nums;

    public OutBDArray(int[] nums) {
        this.nums = nums;
    }

    /**
     * 快速选择算法，在给定的Nums中找到第K大的数
     *
     * @param k    第K个
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
}
