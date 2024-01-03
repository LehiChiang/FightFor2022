package leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class findOrderOffer2Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap();
        for (int i = 0; i < numCourses; ++i)
            graph.put(i, new ArrayList<>());
        int[] indeg = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
            indeg[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indeg.length; i++)
            if (indeg[i] == 0) queue.offer(i);
        int[] res = new int[numCourses];
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            res[count++] = node;
            for (int adjCourse : graph.get(node)) {
                indeg[adjCourse]--;
                if (indeg[adjCourse] == 0)
                    queue.offer(adjCourse);
            }
        }
        return count == numCourses ? res : new int[]{};
    }

    public static void main(String[] args) {
        findOrderOffer2Solution solution = new findOrderOffer2Solution();
        System.out.println(Arrays.toString(solution.findOrder(2, new int[][]{})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
