package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class findGCDSolution {
    public int findGCD(int[] nums) {
        int min = nums[0], max = nums[0];
        for (int num : nums) {
            if (num > max)
                max = num;
            if (num < min)
                min = num;
        }
        return getGCD(min, max);
    }

    private int getGCD(int min, int max) {
        if (max % min == 0)
            return min;
        return getGCD(max % min, min);
    }

    public static void main(String[] args) {
        findGCDSolution solution = new findGCDSolution();
        System.out.println(solution.getGCD(8, 12));
        System.out.println(solution.findGCD(new int[]{3,3}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
