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
class solveNQueensSolution {

    private List<List<String>> res;
    private Set<Integer> inColumns;
    private Set<Integer> inRL;
    private Set<Integer> inLR;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        inColumns = new HashSet<>();
        inRL = new HashSet<>();
        inLR = new HashSet<>();
        // queens表示皇后的位置queens[i] = j 表示皇后在第i行和第j列
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        dfs(n, queens, 0);
        return res;
    }

    // 行控制
    private void dfs(int n, int[] queens, int row) {
        if (row == n) {
            res.add(generateBoard(queens, n));
            return;
        }
        // 这里表示列
        for (int col = 0; col < n; col++) {
            if (inColumns.contains(col))
                continue;
            int rl = row - col;
            if (inRL.contains(rl))
                continue;
            int lr = row + col;
            if (inLR.contains(lr))
                continue;
            queens[row] = col;
            inColumns.add(col);
            inRL.add(rl);
            inLR.add(lr);
            dfs(n, queens, row + 1);
            queens[row] = -1;
            inColumns.remove(col);
            inRL.remove(rl);
            inLR.remove(lr);
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
        solveNQueensSolution solution = new solveNQueensSolution();
        for (List<String> list : solution.solveNQueens(9)) {
            System.out.println(list);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
