package leetcode.editor.cn;


import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class containsNearbyAlmostDuplicateOffer2Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long ceil = set.ceiling((long) nums[i] - (long) t);
            if (ceil != null && ceil <= (long) nums[i] + (long) t)
                return true;
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        containsNearbyAlmostDuplicateOffer2Solution solution = new containsNearbyAlmostDuplicateOffer2Solution();
        System.out.println(solution.containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
