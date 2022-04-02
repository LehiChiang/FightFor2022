package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class shortestPathBinaryMatrixSolution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1)
            return -1;
        if (grid.length == 1)
            return 1;
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int dis = 1;
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                int[] curPos = queue.poll();
                int oriX = curPos[0], oriY = curPos[1];
                for (int dir = 0; dir < dirs.length; dir++) {
                    int newX = oriX + dirs[dir][0];
                    int newY = oriY + dirs[dir][1];
                    if (newX >= 0 && newX < n && newY >= 0 && newY < n &&
                            grid[newX][newY] == 0 && !visited[newX][newY]) {
                        // 先到这里的先结束，提前获取到终点，这个时候的路径就是最短的。
                        if (newX == n - 1 && newY == n - 1)
                            return ++dis;
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            dis++;
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
