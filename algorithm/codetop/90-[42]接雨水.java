package codetop;

class trapSolution {

    public int trap(int[] height) {
        int res = 0;
        int left_max = height[0], right_max = height[height.length - 1];
        int left = 0, right = height.length - 1;
        while (left <= right) {
            if (height[left] > left_max) left_max = height[left];
            if (height[right] > right_max) right_max = height[right];

            if (left_max < right_max) {
                res += left_max - height[left];
                left++;
            } else {
                res += right_max - height[right];
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        trapSolution solution = new trapSolution();
        System.out.println(solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
