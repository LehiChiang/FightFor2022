package leetcode.editor.cn;//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 
// 
// 
// Related Topics 数组 回溯 👍 1134 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class solveNQueens2Solution {

    private List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        // queens表示皇后的位置queens[i] = j 表示皇后在第i行和第j列
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        dfs(n, queens, 0, 0, 0, 0);
        return res;
    }

    // 行控制
    private void dfs(int n, int[] queens, int row, int col, int rl, int lr) {
        if (row == n) {
            res.add(generateBoard(queens, n));
            return;
        }
        int availableCols = (~(col | rl | lr)) & ((1 << n) - 1);
        while (availableCols > 0) {
            int pos = availableCols & (-availableCols);
            availableCols = availableCols & (availableCols - 1);
            queens[row] = Integer.bitCount(pos - 1);
            dfs(n, queens,  row + 1, col | pos, (rl | pos) << 1, (lr | pos) >> 1);
            queens[row] = -1;
        }
    }

    private List<String> generateBoard(int[] queens, int n) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[queens[i]] = 'Q';
            stringList.add(new String(chars));
        }
        return stringList;
    }

    public static void main(String[] args) {
        solveNQueens2Solution solution = new solveNQueens2Solution();
        for (List<String> list : solution.solveNQueens(4)) {
            System.out.println(list);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
