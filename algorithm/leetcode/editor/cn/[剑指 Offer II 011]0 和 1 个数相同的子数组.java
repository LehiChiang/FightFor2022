package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class findMaxLengthOffer2Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0, preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(preSum))
                res = Math.max(res, i - map.get(preSum));
            else
                map.put(preSum, map.getOrDefault(preSum, i));
        }
        return res;
    }

    public static void main(String[] args) {
        findMaxLengthOffer2Solution solution = new findMaxLengthOffer2Solution();
        System.out.println(solution.findMaxLength(new int[]{1,0,0,1,1,1}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
