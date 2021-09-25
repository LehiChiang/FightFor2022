package leetcode.editor.cn;
//给一个有 n 个结点的有向无环图，找到所有从 0 到 n-1 的路径并输出（不要求按顺序）
// 二维数组的第 i 个数组中的单元都表示有向图中 i 号结点所能到达的下一些结点（译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a ）空就是没
//有下一个结点了。
// 示例 1：
//输入：graph = [[1,2],[3],[3],[]]
//输出：[[0,1,3],[0,2,3]]
//解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
// 示例 2：
//输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
//输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
// 示例 3：
//输入：graph = [[1],[]]
//输出：[[0,1]]
// 示例 4：
//输入：graph = [[1,2,3],[2],[3],[]]
//输出：[[0,1,2,3],[0,2,3],[0,3]]
// 示例 5：
//输入：graph = [[1,3],[2],[3],[]]
//输出：[[0,1,2,3],[0,3]]
// 提示：
// n == graph.length 
// 2 <= n <= 15 
// 0 <= graph[i][j] < n 
// graph[i][j] != i 
// 保证输入为有向无环图 (GAD)
// Related Topics 深度优先搜索 广度优先搜索 图 回溯 👍 143 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class allPathsSourceTargetSolution {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // graph是图的邻接表
        traverse(graph, 0, new LinkedList<>());
        return res;
    }

    private void traverse(int[][] graph, int startNode, LinkedList<Integer> path) {
        path.addLast(startNode);
        int n = graph.length;
        if (startNode == n - 1) {
            res.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }
        for (int neighbor: graph[startNode]) {
            traverse(graph, neighbor, path);
        }
        path.removeLast();
    }

    public static void main(String[] args) {
        allPathsSourceTargetSolution solution = new allPathsSourceTargetSolution();
        List<List<Integer>> res = solution.allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}});
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
