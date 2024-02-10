package leetcode.editor.cn;//现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中
//prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。 
//
// 
// 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。 
// 
//
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：[0,1]
//解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
// 
//
// 示例 2： 
//
// 
//输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//输出：[0,2,1,3]
//解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
//因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。 
//
// 示例 3： 
//
// 
//输入：numCourses = 1, prerequisites = []
//输出：[0]
// 
//
// 
//提示：
//
// 
// 1 <= numCourses <= 2000 
// 0 <= prerequisites.length <= numCourses * (numCourses - 1) 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// ai != bi 
// 所有[ai, bi] 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 526 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class findOrderSolution {

    private int[] res;
    private int[] visited;
    private boolean isValid;
    private int index;
    private Map<Integer, List<Integer>> graph;

    public static void main(String[] args) {
        findOrderSolution solution = new findOrderSolution();
        System.out.println(Arrays.toString(solution.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
    }

    // Broad  Search
    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList());
        }
        int[] inDegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]);
            inDegree[pair[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++)
            if (inDegree[i] == 0) queue.offer(i);

        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            res[count++] = node;
            for (int adjNode : graph.get(node)) {
                inDegree[adjNode]--;
                if (inDegree[adjNode] == 0) {
                    queue.offer(adjNode);
                }
            }
        }
//        while (!queue.isEmpty()) {
//            int course = queue.poll();
//            res[count++] = course;
//            for (int adjCourse : graph.get(course)) {
//                inDegree[adjCourse]--;
//                if (inDegree[adjCourse] == 0)
//                    queue.offer(adjCourse);
//            }
//        }
        if (count != numCourses)
            return new int[]{};
        return res;
    }

//    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
//        res = new int[numCourses];
//        visited = new int[numCourses];
//        isValid = true;
//        index = numCourses - 1;
//        graph = new HashMap<>();
//        for (int i = 0; i < numCourses; i++) {
//            graph.put(i, new ArrayList());
//        }
//        for (int[] pair : prerequisites) {
//            graph.get(pair[1]).add(pair[0]);
//        }
//        for (int i = 0; i < numCourses && isValid; i++) {
//            if (visited[i] == 0)
//                dfs(i);
//        }
//        if (!isValid)
//            return new int[]{};
//        return res;
//    }

//    private void dfs(int u) {
//        visited[u] = 1;
//        for (int v : graph.get(u)) {
//            if (visited[v] == 0) {
//                dfs(v);
//                if (!isValid)
//                    return;
//            } else if (visited[v] == 1) {
//                isValid = false;
//                return;
//            }
//        }
//        visited[u] = 2;
//        res[index--] = u;
//    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return findOrderBFS(numCourses, prerequisites);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
