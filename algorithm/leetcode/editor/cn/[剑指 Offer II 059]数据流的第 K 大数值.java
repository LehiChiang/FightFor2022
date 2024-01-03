package leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class KthLargest {

    private PriorityQueue<Integer> queue;
    private int K;
    public KthLargest(int k, int[] nums) {
        K = k;
        queue = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            if (i >= k) {
                queue.poll();
            }
        }
    }
    
    public int add(int val) {
        queue.offer(val);
        if (queue.size() > K)
            queue.poll();
        return queue.peek();
    }

    public static void main(String[] args) {
        KthLargest solution = new KthLargest(3, new int[]{4,5,8,2});
        System.out.println(solution.add(3));
        System.out.println(solution.add(5));
        System.out.println(solution.add(10));
        System.out.println(solution.add(9));
        System.out.println(solution.add(4));
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
//leetcode submit region end(Prohibit modification and deletion)
