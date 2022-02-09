package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class minCostOffer2Solution {
    public int minCost(int[][] costs) {
        int red = costs[0][0], blue = costs[0][1], green = costs[0][2];
        int newRed, newBlue, newGreen;
        for (int i = 1; i < costs.length; i++) {
            newRed = Math.min(blue, green) + costs[i][0];
            newBlue = Math.min(red, green) + costs[i][1];
            newGreen = Math.min(red, blue) + costs[i][2];
            red = newRed;
            blue = newBlue;
            green = newGreen;
        }
        return Math.min(Math.min(red, blue), green);
    }

    public static void main(String[] args) {
        minCostOffer2Solution solution = new minCostOffer2Solution();
        System.out.println(solution.minCost(new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
