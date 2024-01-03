package leetcode.editor.cn;
//给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
// 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
// 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
// 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
// 示例 1：
//输入：mat = 
//[[1,1,0,0,0],
// [1,1,1,1,0],
// [1,0,0,0,0],
// [1,1,0,0,0],
// [1,1,1,1,1]], 
//k = 3
//输出：[2,0,3]
//解释：
//每行中的军人数目：
//行 0 -> 2 
//行 1 -> 4 
//行 2 -> 1 
//行 3 -> 2 
//行 4 -> 5 
//从最弱到最强对这些行排序后得到 [2,0,3,1,4]
// 示例 2：
//输入：mat = 
//[[1,0,0,0],
//[1,1,1,1],
//[1,0,0,0],
//[1,0,0,0]],
//k = 2
//输出：[0,2]
//解释： 
//每行中的军人数目：
//行 0 -> 1 
//行 1 -> 4 
//行 2 -> 1 
//行 3 -> 1 
//从最弱到最强对这些行排序后得到 [0,2,3,1]
// 提示：
// m == mat.length 
// n == mat[i].length 
// 2 <= n, m <= 100 
// 1 <= k <= m 
// matrix[i][j] 不是 0 就是 1 
// 
// Related Topics 数组 二分查找 矩阵 排序 堆（优先队列） 
// 👍 78 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class kWeakestRowsSolution {
    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((pair1, pair2) -> {
            if (pair1[0] != pair2[0])
                return pair1[0] - pair2[0];
            else return pair1[1] - pair2[1];
        });
        for (int i = 0 ; i < mat.length ; i ++) {
            int solider = countSolder(mat, i, mat[i].length);
            queue.offer(new int[]{solider, i});
        }

        int[] res = new int[k];
        for (int i = 0 ; i < k ; i ++){
            res[i] = queue.poll()[1];
        }
        return res;
    }

    public int countSolder(int[][] mat, int row, int col){
        int low = 0, high = col - 1;
        int mid, pos = 0;
        while (low <= high){
            mid = (low + high) / 2;
            if (mat[row][mid] == 0)
                high = mid - 1;
            else {
                pos = mid;
                low = mid + 1;
            }
        }
        return pos + 1;
    }

    public static void main(String[] args) {
        kWeakestRowsSolution solution = new kWeakestRowsSolution();
        int[] res = solution.kWeakestRows(new int[][]{{1,1,0,0,0},
                {1,1,1,1,0},
                {1,0,0,0,0},
                {1,1,0,0,0},
                {1,1,1,1,1}}, 3);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
