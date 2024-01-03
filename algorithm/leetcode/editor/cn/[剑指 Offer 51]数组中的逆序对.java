package leetcode.editor.cn;//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 582 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class reversePairsSolution {

    private int[] nums, tmp;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        this.tmp = new int[nums.length];
        return mergeSort(0, nums.length - 1);

    }

    private int mergeSort(int l, int r) {
        if (l >= r)
            return 0;
        int mid = l + (r - l) / 2;
        return mergeSort(l, mid) + mergeSort(mid + 1, r) + merge(l, mid, r);
    }

    private int merge(int l, int mid, int r) {
        int res = 0;
        for (int i = l ; i <= r; i++)
            tmp[i] = nums[i];
        int p1 = l, p2 = mid + 1, p = l;
        while (p1 <= mid && p2 <= r) {
            if (tmp[p1] > tmp[p2]) {
                nums[p++] = tmp[p2++];
                res += mid - p1 + 1;
            }
            else
                nums[p++] = tmp[p1++];
        }
        while (p1 <= mid)
            nums[p++] = tmp[p1++];
        while (p2 <= r)
            nums[p++] = tmp[p2++];
        return res;
    }

    public static void main(String[] args) {
        reversePairsSolution solution = new reversePairsSolution();
        System.out.println(solution.reversePairs(new int[]{7,5,6,4}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
