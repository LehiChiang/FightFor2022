package leetcode.editor.cn;

import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class findMaximumXOROffer2Solution {
    public static void main(String[] args) {
        findMaximumXOROffer2Solution solution = new findMaximumXOROffer2Solution();
        System.out.println(solution.findMaximumXOR(new int[]{2}));
    }

    public int findMaximumXOR(int[] nums) {
        int mask = 0, res = 0;
        for (int i = 30; i >= 0; i--) {
            mask = mask | (1 << i);
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }
            int localRes = res | (1 << i);
            for (int prefix : set) {
                if (set.contains(prefix ^ localRes)) {
                    res = localRes;
                    break;
                }
            }
        }
        return res;
//        int mask = 0, res = 0;
//        for (int i = 30; i >= 0; i--) {
//            mask = mask | (1 << i);
//            Set<Integer> set = new HashSet<>();
//            for (int num : nums) {
//                set.add(num & mask);
//            }
//            int targetMax = res | (1 << i);
//            for (int prefix : set) {
//                if (set.contains(targetMax ^ prefix)) {
//                    res = targetMax;
//                    break;
//                }
//            }
//        }
//        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
