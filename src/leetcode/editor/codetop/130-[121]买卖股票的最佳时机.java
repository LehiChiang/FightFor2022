package leetcode.editor.codetop;

class maxProfitSolution {
    public int maxProfit(int[] prices) {
        int res = 0;
        int max = prices[0], min = prices[0];
        for (int price : prices) {
            if (price > max) {
                max = price;
                res = Math.max(res, max - min);
            }
            else if (price < min) {
                min = price;
                max = min;
            }
        }
        return res;
    }

    public int maxProfit2(int[] prices) {
        int max = 0, min = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < min) {
                min = price;
            }
            else if (price - min > max) {
                max = price - min;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        maxProfitSolution solution = new maxProfitSolution();
        System.out.println(solution.maxProfit(new int[]{2, 4, 1}));
    }
}
