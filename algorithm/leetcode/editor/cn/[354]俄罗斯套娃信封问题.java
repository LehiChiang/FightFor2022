package leetcode.editor.cn;

//给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
// 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
// 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
// 注意：不允许旋转信封。
// 示例 1：
//输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出：3
//解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
// 示例 2：
//输入：envelopes = [[1,1],[1,1],[1,1]]
//输出：1
// 提示：
// 1 <= envelopes.length <= 5000 
// envelopes[i].length == 2 
// 1 <= wi, hi <= 10⁴
// Related Topics 数组 二分查找 动态规划 排序 👍 633 👎 0


import utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class maxEnvelopesSolution {

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0])
                return o2[1] - o1[1];
            else
                return o1[0] - o2[0];
        });
        List<Integer> list = new ArrayList<>();
        list.add(envelopes[0][1]);
        for (int i = 1; i < envelopes.length; i++) {
            if (envelopes[i][1] > list.get(list.size() - 1)) {
                list.add(envelopes[i][1]);
            } else {
                int index = binarysearch(list, envelopes[i][1]);
                list.set(index, envelopes[i][1]);
            }
        }
        return list.size();
    }

    private int binarysearch(List<Integer> list, int num) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > num) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        maxEnvelopesSolution solution = new maxEnvelopesSolution();
        System.out.println(solution.maxEnvelopes(new int[][]{{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
