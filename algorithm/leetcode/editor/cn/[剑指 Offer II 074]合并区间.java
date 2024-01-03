package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class mergeOffer2Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new ArrayList<>();
        res.add(new int[]{intervals[0][0], intervals[0][1]});
        for (int[] interval :intervals) {
            int[] lastInterval = res.get(res.size() - 1);
            if (lastInterval[1] >= interval[0])
                lastInterval[1] = Math.max(lastInterval[1], interval[1]);
            else {
                res.add(new int[]{interval[0], interval[1]});
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        mergeOffer2Solution solution = new mergeOffer2Solution();
        System.out.println(Arrays.deepToString(solution.merge(new int[][]{{4,6}, {1,4}})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
