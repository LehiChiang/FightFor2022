package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;


//leetcode submit region begin(Prohibit modification and deletion)
class MovingAverageOffer2 {

    /**
     * Initialize your data structure here.
     */
    private double sum;
    private int size;
    private Deque<Integer> queue;

    public MovingAverageOffer2(int size) {
        this.sum = 0;
        this.size = size;
        this.queue = new LinkedList<>();
    }

    public static void main(String[] args) {
        MovingAverageOffer2 solution = new MovingAverageOffer2(3);
        System.out.println(solution.next(1));
        System.out.println(solution.next(10));
        System.out.println(solution.next(3));
        System.out.println(solution.next(5));
    }

    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.pollFirst();
        }
        sum += val;
        queue.offerLast(val);
        return sum / queue.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
//leetcode submit region end(Prohibit modification and deletion)
