package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class eraseOverlapIntervalsSolution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[1] - interval2[1];
            }
        });
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= right) {
                ans++;
                right = intervals[i][1];
            }
        }
        return intervals.length - ans;
    }

    public static void main(String[] args) {
        eraseOverlapIntervalsSolution solution = new eraseOverlapIntervalsSolution();
        System.out.println(solution.eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
