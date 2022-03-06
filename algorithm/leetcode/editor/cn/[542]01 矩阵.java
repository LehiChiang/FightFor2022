package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class updateMatrixSolution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0)
                    queue.offer(new int[]{i, j});
                else
                    mat[i][j] = -1;
            }
        }
        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] idx = queue.poll();
            int x = idx[0], y = idx[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i], newY = y + dy[i];
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && mat[newX][newY] == -1) {
                    mat[newX][newY] = mat[x][y] + 1;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
        return mat;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
