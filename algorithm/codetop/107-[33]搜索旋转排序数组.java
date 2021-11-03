package codetop;

class searchSolution {
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;
       int low = 0, high = nums.length;
       while (low < high) {
           int mid = low + (high - low) / 2;
           if (nums[mid] == target)
               return mid;
           if (nums[0] < nums[mid]) {
               if (target >= nums[0] && target < nums[mid]) {
                   high = mid;
               } else {
                   low = mid + 1;
               }
           } else {
               if (target > nums[mid] && target <= nums[nums.length - 1]) {
                   low = mid + 1;
               } else {
                   high = mid;
               }
           }
       }
       return -1;
    }

    public static void main(String[] args) {
        searchSolution solution = new searchSolution();
        System.out.println(solution.search(new int[]{6,7,0,1,2,4,5}, 77));
    }
}
