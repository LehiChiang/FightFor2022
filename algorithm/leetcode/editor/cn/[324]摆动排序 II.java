package leetcode.editor.cn;//给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
//
// 你可以假设所有输入数组都可以得到满足题目要求的结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,1,1,6,4]
//输出：[1,6,1,5,1,4]
//解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,3,2,2,3,1]
//输出：[2,3,1,3,1,2]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// 0 <= nums[i] <= 5000 
// 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果 
// 
//
// 
//
// 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？ 
// Related Topics 数组 分治 快速选择 排序 👍 303 👎 0


import java.util.Arrays;
import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
class wiggleSortSolution {

    private Random random = new Random();
    public void wiggleSort(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 找出中位数
        int mid_index = quickSelect(nums, left, right, left + (right - left) / 2);
        // 左右两边分开
        threePartPartition(nums, mid_index);
        System.out.println(Arrays.toString(nums));
        // 交缠在一起
        int[] leftPart = Arrays.copyOfRange(nums, left, mid_index + 1);
        int[] rightPart = Arrays.copyOfRange(nums, mid_index + 1, nums.length);

        System.out.println(Arrays.toString(leftPart));
        System.out.println(Arrays.toString(rightPart));

        int len = 0, m = leftPart.length - 1, n = rightPart.length - 1;
        boolean fromLeft = true;
        while (len < nums.length) {
            if (fromLeft) {
                nums[len] = leftPart[m--];
                fromLeft = false;
            } else {
                nums[len] = rightPart[n--];
                fromLeft = true;
            }
            len++;
        }

        System.out.println(Arrays.toString(nums));
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        int index = randomPartition(nums, start, end);
        if (k == index)
            return index;
        else if (k < index)
            return quickSelect(nums, start, index - 1, k);
        else
            return quickSelect(nums, index + 1, end, k);
    }

    private int randomPartition(int[] nums, int left, int right) {
        int random_index = random.nextInt(right - left + 1);
        swap(nums, right, left + random_index);
        return partition(nums, left, right);
    }

    private int partition(int[] nums, int start, int end) {
        int i = start - 1, j = start, r = nums[end];
        for (; j < end; j++) {
            if (nums[j] < r) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, ++i, end);
        return i;
    }

    private void threePartPartition(int[] nums, int mid) {
        int r = nums[mid], i = 0, j = 0, end = nums.length - 1;
        for (; j < end; j++) {
            if (nums[j] < r) {
                swap(nums, i++, j++);
            } else if (nums[j] > r) {
                swap(nums, end--, j);
            } else {
                j++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        wiggleSortSolution solution = new wiggleSortSolution();
        solution.wiggleSort(new int[]{1,3,2,2,3,1});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
