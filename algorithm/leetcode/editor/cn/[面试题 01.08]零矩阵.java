package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class setZeroesSolutionInterview {
    public void setZeroes(int[][] matrix) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0)
                    list.add(new int[]{i, j});
            }
        }

        for (int[] pair : list) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (i == pair[0] || j == pair[1]) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
