package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class findMaxAverageSolution {
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0, maxAvg = -10000000;
        int start = 0, end = 0;
        while (end < nums.length) {
            sum += nums[end];
            if (end - start + 1 == k) {
                maxAvg = Math.max(maxAvg, sum / k);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return maxAvg;
    }

    public static void main(String[] args) {
        findMaxAverageSolution solution = new findMaxAverageSolution();
        System.out.println(solution.findMaxAverage(new int[]{-5}, 1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
