package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class divideSolution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        if (divisor == 1)
            return dividend;
        if (dividend == 0)
            return 0;
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0))
            return getDivide(Math.abs((long) dividend), Math.abs((long) divisor));
        else
            return -getDivide(Math.abs((long) dividend), Math.abs((long) divisor));
    }

    private int getDivide(long dividend, long divisor) {
        if (dividend < divisor)
            return 0;
        long num = divisor;
        int res = 1;
        while ((num + num) <= dividend) {
            num += num;
            res += res;
        }
        return res + getDivide(dividend - num, divisor);
    }

    public static void main(String[] args) {
        divideSolution solution = new divideSolution();
        System.out.println(solution.divide(-2147483648, 2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
