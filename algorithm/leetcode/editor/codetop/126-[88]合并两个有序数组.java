package leetcode.editor.codetop;

import java.util.Arrays;

import static leetcode.editor.utils.ArrayUtils.printIntArray;

class mergeTwoSortedArraySolution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int last = n + m - 1;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                nums1[last] = nums2[p2];
                p2--;
            } else if (p2 == -1) {
                nums1[last] = nums1[p1];
                p1--;
            } else if (nums1[p1] > nums2[p2]) {
                nums1[last] = nums1[p1];
                p1--;
            } else {
                nums1[last] = nums2[p2];
                p2--;
            }
            last--;
        }
    }

    public static void main(String[] args) {
        mergeTwoSortedArraySolution solution = new mergeTwoSortedArraySolution();
        solution.merge(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
    }
}
