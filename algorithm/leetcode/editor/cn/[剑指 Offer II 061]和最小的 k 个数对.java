package leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class kSmallestPairsSolution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(indexPair -> nums1[indexPair[0]] + nums2[indexPair[1]]));
        int m = nums1.length, n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++)
            queue.offer(new int[]{i, 0});
        while (k-- > 0 && !queue.isEmpty()) {
            int[] pair = queue.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[pair[0]]);
            list.add(nums2[pair[1]]);
            res.add(list);
            if (pair[1] + 1 < n) {
                queue.offer(new int[]{pair[0], pair[1] + 1});
            }
        }
        return res;
    }


    public static void main(String[] args) {
        kSmallestPairsSolution solution = new kSmallestPairsSolution();
        System.out.println(solution.kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6}, 3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)