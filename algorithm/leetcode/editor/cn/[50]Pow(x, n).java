package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class myPowSolution {
    public double myPow(double x, int n) {
        if (x == 1)
            return x;
        return n < 0 ? 1.0 / getPow(x, -(long) n) : getPow(x, (long) n);
    }

    private double getPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double y = getPow(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }

    public static void main(String[] args) {
        myPowSolution solution = new myPowSolution();
        System.out.println(solution.myPow(2, -2147483648));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
