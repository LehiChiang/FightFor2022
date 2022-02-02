package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class minEatingSpeedOffer2Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 1_000_000_000;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int hours = getHours(piles, mid);
            if (hours == h)
                right = mid;
            else if (hours > h)
                left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private int getHours(int[] piles, int speed) {
        int hours = 0;
        for (int pile : piles) {
            hours += pile / speed;
            if (pile % speed != 0)
                hours++;
        }
        return hours;
    }

    public static void main(String[] args) {
        minEatingSpeedOffer2Solution solution = new minEatingSpeedOffer2Solution();
        System.out.println(solution.minEatingSpeed(new int[]{3,6,7,11}, 8));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
