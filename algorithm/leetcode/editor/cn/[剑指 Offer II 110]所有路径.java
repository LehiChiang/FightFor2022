package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class allPathsSourceTargetOffer2Solution {

    public static void main(String[] args) {
        allPathsSourceTargetOffer2Solution solution = new allPathsSourceTargetOffer2Solution();
        System.out.println(solution.allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}}));
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>() {{
            add(0);
        }});
        while (!queue.isEmpty()) {
            List<Integer> list = queue.poll();
            int node = list.get(list.size() - 1);
            if (node == graph.length - 1)
                res.add(list);
            else {
                for (int v : graph[node]) {
                    List<Integer> newPath = new ArrayList<>(list);
                    newPath.add(v);
                    queue.add(newPath);
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
