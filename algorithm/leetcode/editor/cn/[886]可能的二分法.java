package leetcode.editor.cn;//给定一组 N 人（编号为 1, 2, ..., N）， 我们想把每个人分进任意大小的两组。
//
// 每个人都可能不喜欢其他人，那么他们不应该属于同一组。 
//
// 形式上，如果 dislikes[i] = [a, b]，表示不允许将编号为 a 和 b 的人归入同一组。 
//
// 当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：N = 4, dislikes = [[1,2],[1,3],[2,4]]
//输出：true
//解释：group1 [1,4], group2 [2,3]
// 
//
// 示例 2： 
//
// 
//输入：N = 3, dislikes = [[1,2],[1,3],[2,3]]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 2000 
// 0 <= dislikes.length <= 10000 
// dislikes[i].length == 2 
// 1 <= dislikes[i][j] <= N 
// dislikes[i][0] < dislikes[i][1] 
// 对于 dislikes[i] == dislikes[j] 不存在 i != j 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 👍 146 👎 0


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class possibleBipartitionSolution {

    private boolean isBipartite = true;
    private boolean[] visited;
    private boolean[] color;
    private List<Integer>[] graph;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        graph = buildGraph(n, dislikes);
        visited = new boolean[n + 1];
        color = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                bfs(graph, i);
            }
        }
        return isBipartite;
    }

    private void bfs(List<Integer>[] graph, int u) {
        Queue<Integer> queue = new LinkedList<>();
        visited[u] = true;
        queue.offer(u);
        while (!queue.isEmpty() && isBipartite) {
            int node = queue.poll();
            for (int v : graph[node]) {
                if (!isBipartite)
                    break;
                if (!visited[v]) {
                    color[v] = !color[node];
                    visited[v] = true;
                    queue.offer(v);
                } else {
                    isBipartite = color[v] == color[node] ? false : true;
                }
            }
        }
    }

    private List<Integer>[] buildGraph(int n, int[][] dislikes) {
        graph = new LinkedList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : dislikes) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return graph;
    }

    public static void main(String[] args) {
        possibleBipartitionSolution solution = new possibleBipartitionSolution();
        System.out.println(solution.possibleBipartition(4, new int[][]{{1,2},{3,4},{1,3},{1,4}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
