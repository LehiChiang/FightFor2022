package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private int[] preSum;
    private int sum;
    private Random random;
    public Solution(int[] w) {
        preSum = new int[w.length];
        preSum[0] = w[0];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + w[i];
        }
        sum = preSum[preSum.length - 1];
        random = new Random();
    }
    
    public int pickIndex() {
        int randNum = random.nextInt(sum) + 1;
        return getRandIndex(randNum);
    }

    private int getRandIndex(int randNum) {
        int left = 0, right = preSum.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (preSum[mid] == randNum)
                return mid;
            else if (preSum[mid] < randNum)
                left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{3,1,2,4});
        System.out.println(solution.getRandIndex(10));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
//leetcode submit region end(Prohibit modification and deletion)
