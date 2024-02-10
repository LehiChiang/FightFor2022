package leetcode.editor.cn;


//leetcode submit region begin(Prohibit modification and deletion)
class peakIndexInMountainArrayOffer2Solution {
    public static void main(String[] args) {
        peakIndexInMountainArrayOffer2Solution solution = new peakIndexInMountainArrayOffer2Solution();
        System.out.println(solution.peakIndexInMountainArray(new int[]{0, 1, 0}));
    }

    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
