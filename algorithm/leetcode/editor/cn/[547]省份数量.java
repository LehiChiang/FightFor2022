package leetcode.editor.cn;//
// 
// 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连
//。 
//
// 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。 
//
// 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 
//isConnected[i][j] = 0 表示二者不直接相连。 
//
// 返回矩阵中 省份 的数量。 
//
// 
//
// 示例 1： 
//
// 
//输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 200 
// n == isConnected.length 
// n == isConnected[i].length 
// isConnected[i][j] 为 1 或 0 
// isConnected[i][i] == 1 
// isConnected[i][j] == isConnected[j][i] 
// 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 👍 682 👎 0


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class findCircleNumSolution {

    private int[] parents;
    private int circles;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        parents = new int[n];
        for (int i = 0; i < parents.length; i++)
            parents[i] = i;
        circles = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1)
                    union(i, j);
            }
        }
        return circles;
    }

    public void union(int node1, int node2) {
        int p = find(node1);
        int q = find(node2);
        if (p != q) {
            parents[p] = q;
            circles--;
        }
    }

    public int find(int node) {
        while (parents[node] != node) {
            parents[node] = parents[parents[node]];
            node = parents[node];
        }
        return node;
    }

//    public int findCircleNum(int[][] isConnected) {
//        int m = isConnected.length;
//        boolean[] visited = new boolean[m];
//        int circles = 0;
//        Queue<Integer> queue = new LinkedList<>();
//        for (int i = 0; i < m; i++) {
//            if (!visited[i]) {
//                queue.offer(i);
//                while (!queue.isEmpty()) {
//                    int j = queue.poll();
//                    visited[j] = true;
//                    for (int k = 0; k < isConnected.length; k++) {
//                        if (isConnected[j][k] == 1 && !visited[k]) {
//                            queue.offer(k);
//                        }
//                    }
//                }
//                circles++;
//            }
//        }
//        return circles;
//    }

//    public int findCircleNum(int[][] isConnected) {
//        int m = isConnected.length;
//        boolean[] visited = new boolean[m];
//        int circles = 0;
//        for (int i = 0; i < m; i++) {
//            if (!visited[i]) {
//                dfs(isConnected, visited, i);
//                circles++;
//            }
//        }
//        return circles;
//    }
//
//    private void dfs(int[][] isConnected, boolean[] visited, int i) {
//        for (int j = 0; j < isConnected.length; j++) {
//            if (isConnected[i][j] == 1 && !visited[j]) {
//                visited[j] = true;
//                dfs(isConnected, visited, j);
//            }
//        }
//    }

    public static void main(String[] args) {
        findCircleNumSolution solution = new findCircleNumSolution();
        System.out.println(solution.findCircleNum(new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        }));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
