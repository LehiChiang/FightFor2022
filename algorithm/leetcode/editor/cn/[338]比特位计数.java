package leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class countBitsSolution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++)
             res[i] = res[i & (i - 1)] + 1;
        return res;
    }

    public static void main(String[] args) {
        countBitsSolution solution = new countBitsSolution();
        System.out.println(Arrays.toString(solution.countBits(5)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
