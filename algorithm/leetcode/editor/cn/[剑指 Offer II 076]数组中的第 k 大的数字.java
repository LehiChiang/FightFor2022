package leetcode.editor.cn;

import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
class findKthLargestOffer2Solution {


    private Random random;

    public static void main(String[] args) {
        findKthLargestOffer2Solution solution = new findKthLargestOffer2Solution();
        System.out.println(solution.findKthLargest(new int[]{-1, 2, 0}, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        random = new Random();
        return quickFind(nums, 0, nums.length - 1, k);
    }

    private int quickFind(int[] nums, int left, int right, int k) {
        if (left >= right)
            return nums[left];
        int index = random_partition(nums, left, right);
        if (index == nums.length - k)
            return nums[index];
        else if (index < nums.length - k)
            return quickFind(nums, index + 1, right, k);
        else
            return quickFind(nums, left, index - 1, k);
    }

    private int random_partition(int[] nums, int left, int right) {
        int random_index = left + random.nextInt(right - left + 1);
        swap(nums, left, random_index);
        return partition(nums, left, right);
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) right--;
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) left++;
            nums[right] = nums[left];
        }
        nums[right] = pivot;
        return right;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
