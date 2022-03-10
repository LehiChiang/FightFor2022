package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class rotatedDigitsSolution {
    public int rotatedDigits(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (isGoodNum(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isGoodNum(Integer num) {
        char[] digits = num.toString().toCharArray();
        int sameCnt = 0;
        for (char ch : digits) {
            if (ch == '0' || ch == '1' || ch == '8') {
                sameCnt++;
            } else if (ch == '2' || ch == '5' || ch == '6' || ch == '9') {
                continue;
            } else {
                return false;
            }
        }
        if (sameCnt == digits.length)
            return false;
        else
            return true;
    }

    public static void main(String[] args) {
        rotatedDigitsSolution solution = new rotatedDigitsSolution();
        System.out.println(solution.rotatedDigits(10));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
