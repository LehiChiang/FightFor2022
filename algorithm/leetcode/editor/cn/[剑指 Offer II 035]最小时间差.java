package leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class findMinDifferenceOffer2Solution {
    public static void main(String[] args) {
        findMinDifferenceOffer2Solution solution = new findMinDifferenceOffer2Solution();
        List<String> timePoints = new ArrayList<>();
        timePoints.add("05:31");
        timePoints.add("22:08");
        timePoints.add("00:35");
        System.out.println(solution.findMinDifference(timePoints));
    }

    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440)
            return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (String time : timePoints) {
            if (time.equals("00:00")) {
                queue.offer(1440);
                continue;
            }
            queue.offer(Integer.parseInt(time.substring(0, time.indexOf(":"))) * 60 + Integer.parseInt(time.substring(time.indexOf(":") + 1)));
        }
        int minInterval = Integer.MAX_VALUE;
        int min = queue.peek();
        while (queue.size() != 1) {
            minInterval = Math.min(minInterval, -(queue.poll() - queue.peek()));
        }
        minInterval = Math.min(minInterval, 1440 - queue.poll() + min);
        return minInterval;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
