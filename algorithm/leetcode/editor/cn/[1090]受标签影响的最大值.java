package leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class largestValsFromLabelsSolution {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < values.length; i++) {
            queue.offer(new int[]{values[i], labels[i]});
        }

        Arrays.sort(labels);
        int[] limCnt = new int[labels[labels.length - 1] + 1];
        Arrays.fill(limCnt, useLimit);
        int sum = 0;
        while (numWanted > 0 && !queue.isEmpty()) {
            int[] entry = queue.poll();
            if (limCnt[entry[1]] > 0) {
                sum += entry[0];
                numWanted--;
                limCnt[entry[1]]--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        largestValsFromLabelsSolution solution = new largestValsFromLabelsSolution();
        System.out.println(solution.largestValsFromLabels(new int[]{4,7,4,6,3}, new int[]{2,0,0,2,2}, 1, 2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
