package leetcode.editor.cn;
//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 示例 1：
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2：
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 提示：
// 1 <= numCourses <= 10⁵ 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1057 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class canFinishCourseSolution {

    private List<List<Integer>> edges;
    private int[] visited;
    private int[] indeg;
    boolean isValid = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return bfdCanFinish(numCourses, prerequisites);
    }

    private boolean bfdCanFinish(int numCourses, int[][] prerequisites) {
        // 图的邻接表存储
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i)
            edges.add(new ArrayList<>());
        // 存节点的入度信息，方便从入度为0的节点开始进行广度优先搜索
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            indeg[info[0]]++;
        }

        // 先将入度为0的节点放入队列中
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) queue.offer(i);
        }

        // 由于不需要输出序列，所以直接计数就行，遍历过的节点计数，不用使用数据结构保存
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int node = queue.poll();
            for (int v : edges.get(node)) {
                indeg[v]--;
                if (indeg[v] == 0)
                    queue.offer(v);
            }
        }
        return res == numCourses;
    }


    private boolean dfsCanFisnish(int numCourses, int[][] prerequisites) {
        // 图的邻接表存储
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            edges.add(new ArrayList<>());
        // 判断节点i是否被访问，0表示未访问，1表示被访问过
        visited = new int[numCourses];
        // 建立图
        for (int[] preRequisite : prerequisites) {
            edges.get(preRequisite[1]).add(preRequisite[0]);
        }
        System.out.println(edges);
        // 开始深度优先遍历所有节点
        for (int i = 0; i < numCourses && isValid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return isValid;
    }


    private void dfs(int i) {
        // 正在访问节点（包括其子节点）
        visited[i] = 1;
        for (int v : edges.get(i)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!isValid) {
                    return;
                }
            } else if (visited[v] == 1) { // 则存在环，返回false
                isValid = false;
                return;
            }
        }
        visited[i] = 2;
    }

    public static void main(String[] args) {
        canFinishCourseSolution solution = new canFinishCourseSolution();
        System.out.println(solution.canFinish(3, new int[][]{{1,0}, {2,0}, {0,1}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
