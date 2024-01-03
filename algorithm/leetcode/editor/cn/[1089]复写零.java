package leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class duplicateZerosSolution {
    public void duplicateZeros(int[] arr) {
        int start = 0;
        while (start < arr.length) {
            if (arr[start] == 0) {
                for (int j = arr.length - 1; j > start; j--) {
                    arr[j] = arr[j - 1];
                }
                start += 2;
            } else {
                start++;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        duplicateZerosSolution solution = new duplicateZerosSolution();
        solution.duplicateZeros(new int[]{1,2,3});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
