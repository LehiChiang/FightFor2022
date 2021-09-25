package leetcode.editor.cn;
//给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中
// i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
// 返回平面上所有回旋镖的数量。 

// 示例 1：
//输入：points = [[0,0],[1,0],[2,0]]
//输出：2
//解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
//
// 示例 2：
//输入：points = [[1,1],[2,2],[3,3]]
//输出：2
//
// 示例 3：
//输入：points = [[1,1]]
//输出：0
//
// 提示：
// n == points.length 
// 1 <= n <= 500 
// points[i].length == 2 
// -10⁴ <= xi, yi <= 10⁴ 
// 所有点都 互不相同 
// 
// Related Topics 数组 哈希表 数学 👍 171 👎 0

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class numberOfBoomerangsSolution {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        int n = points.length;
        if (n < 3) return res;
        for (int[] curPoint : points) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int [] iterPoint :  points) {
                int dist = (curPoint[0] - iterPoint[0]) * (curPoint[0] - iterPoint[0]) + (curPoint[1] - iterPoint[1]) * (curPoint[1] - iterPoint[1]);
                map.put(dist, map.getOrDefault(dist, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int count = entry.getValue();
                res += count * (count - 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        numberOfBoomerangsSolution solution = new numberOfBoomerangsSolution();
        System.out.println(solution.numberOfBoomerangs(new int[][]{{0,0}, {1,0}, {-1,0}, {0,1}, {0,-1}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
