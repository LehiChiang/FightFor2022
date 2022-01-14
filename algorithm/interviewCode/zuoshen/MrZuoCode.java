package interviewCode.zuoshen;

public class MrZuoCode {

    public static void main(String[] args) {

    }

    public int minSwapStep(String s) {
        int BStart = 0, BSwapCount = 0;
        int GStart = 0, GSwapCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'B') {
                BSwapCount += i - BStart;
                BStart++;
            }
            if (s.charAt(i) == 'G') {
                GSwapCount += i - GStart;
                GStart++;
            }
        }
        return Math.min(BSwapCount, GSwapCount);
    }

    public int maxCover(int[] nums, int len) {
        int max = 0;
        int left = 0, right = 0;
        while (left < nums.length) {
            while (right < nums.length && nums[right] - nums[left] <= len)
                right++;
            max = Math.max(max, right - left);
            left++;
        }
        return max;
    }


}
