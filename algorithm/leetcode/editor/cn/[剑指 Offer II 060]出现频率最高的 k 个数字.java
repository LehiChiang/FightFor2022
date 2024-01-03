package leetcode.editor.cn;


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class topKFrequentSolution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(pair -> pair[1]));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            queue.offer(new int[]{num, count});
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++)
            res[i] = queue.poll()[0];
        return res;
    }

    public static void main(String[] args) {
        topKFrequentSolution solution = new topKFrequentSolution();
        System.out.println(Arrays.toString(solution.topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
