package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class maxAreaOfIslandOffer2Solution {

    private int area = 0;

    public static void main(String[] args) {
        maxAreaOfIslandOffer2Solution solution = new maxAreaOfIslandOffer2Solution();
        System.out.println(solution.maxAreaOfIsland(
                new int[][]{
                        {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
                }));
    }

    public int maxAreaOfIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    area = Math.max(dfs(grid, i, j), area);
                }
            }
        return area;
//         for (int i = 0; i < grid.length; i++) {
//             for (int j = 0; j < grid[i].length; j++) {
//                 area = Math.max(dfs(grid, i, j), area);
//             }
//         }
//         return area;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0)
            return 0;
        int area = grid[i][j] == 1 ? 1 : 0;
        grid[i][j] = 0;
        area += dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1);
        return area;
//        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j]== 0)
//            return 0;
//        int area = grid[i][j] == 1 ? 1 : 0;
//        grid[i][j] = 0;
//        area += dfs(grid, i + 1, j);
//        area += dfs(grid, i - 1, j);
//        area += dfs(grid, i, j + 1);
//        area += dfs(grid, i, j - 1);
//        return area;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
