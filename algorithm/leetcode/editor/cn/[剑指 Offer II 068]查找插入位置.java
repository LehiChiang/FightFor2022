package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class searchInsertOffer2Solution {
    public static void main(String[] args) {
        searchInsertOffer2Solution solution = new searchInsertOffer2Solution();
        System.out.println(solution.searchInsert(new int[]{1, 3, 5, 6}, 2));
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
//        int left = 0, right = nums.length;
//        while (left < right) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] == target)
//                return mid;
//            else if (nums[mid] > target)
//                right = mid;
//            else left = mid + 1;
//        }
//        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
