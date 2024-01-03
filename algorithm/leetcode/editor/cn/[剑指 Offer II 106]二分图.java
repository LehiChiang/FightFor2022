package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class isBipartiteOffer2Solution {

    private boolean isBipartite = true;
    private boolean[] color;
    private boolean[] visited;
    public boolean isBipartite(int[][] graph) {
        color = new boolean[graph.length];
        visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                bfs(graph, i);
            }
        }
        return isBipartite;
    }

    private void bfs(int[][] graph, int v) {
        Queue<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.offer(v);
        while (!queue.isEmpty() && isBipartite) {
            int u = queue.poll();
            for (int w : graph[u]) {
                if (!visited[w]) {
                    color[w] = !color[u];
                    visited[w] = true;
                    queue.offer(w);
                } else {
                    if (color[u] == color[w])
                        isBipartite = false;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
