package leetcode.editor.cn;
//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
// 示例 1:
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 示例 2:
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4
// 提示：
// 1 <= k <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1411 👎 0


import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
class findKthLargestSolution {

    private Random random = new Random();

    // 寻找nums中倒数第nums.length - k 位置上的数字
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, k, 0, nums.length - 1);
    }

    private int quickSelect(int[] nums, int k, int left, int right) {
        int index = random_partition(nums, left, right);
        if (index == nums.length - k)
            return nums[nums.length - k];
        else if (index < nums.length - k)
            return quickSelect(nums, k, index + 1, right);
        else
            return quickSelect(nums, k, left, index - 1);
    }

    private int random_partition(int[] nums, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        swap(nums, left, i);
        return partition(nums, left, right);
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private int partition(int[] nums, int left, int right) {
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

    public static void main(String[] args) {
        findKthLargestSolution solution = new findKthLargestSolution();
        System.out.println(solution.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
