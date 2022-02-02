package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class findMaximumXOROffer2Solution {
    public int findMaximumXOR(int[] nums) {
        int mask = 0, res = 0;
        for (int i = 30; i >= 0; i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }
            int targetMax = res | (1 << i);
            for (int prefix : set) {
                if (set.contains(targetMax ^ prefix)) {
                    res = targetMax;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        findMaximumXOROffer2Solution solution = new findMaximumXOROffer2Solution();
        System.out.println(solution.findMaximumXOR(new int[]{3,10,5,25,2,8}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
