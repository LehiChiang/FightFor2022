package leetcode.editor.cn;
//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
// 示例 1：
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
// 示例 2：
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 提示：
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴
// Related Topics 数组 排序 👍 1155 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class mergeSolution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(pair -> pair[0]));
        list.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int end = list.get(list.size() - 1)[1];
            if (end >= intervals[i][0]) {
                list.get(list.size() - 1)[1] = Math.max(end, intervals[i][1]);
            } else {
                list.add(intervals[i]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {
        mergeSolution solution = new mergeSolution();
        int[][] res = solution.merge(new int[][]{{1, 4}, {2, 10}});
        System.out.println(Arrays.deepToString(res));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
