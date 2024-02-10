package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class mySqrtOffer2Solution {


    public static void main(String[] args) {
        mySqrtOffer2Solution solution = new mySqrtOffer2Solution();
        System.out.println(solution.mySqrt(2147395599));
    }

    public int mySqrt(int x) {
        if (x == 0 || x == 1)
            return x;
        int left = 0, right = x;
        int res = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                left = mid + 1;
                res = mid;
            } else
                right = mid;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
