package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;


//leetcode submit region begin(Prohibit modification and deletion)
class RecentCounterOffer2 {

    private final int THRESHOLD = 3000;
    private Deque<Integer> queue;

    public RecentCounterOffer2() {
        queue = new LinkedList<>();
    }

    public static void main(String[] args) {
        RecentCounterOffer2 solution = new RecentCounterOffer2();
        System.out.println(solution.ping(1));
        System.out.println(solution.ping(100));
        System.out.println(solution.ping(3001));
        System.out.println(solution.ping(3002));
    }

    public int ping(int t) {
        queue.offerLast(t);
        while (t - THRESHOLD > queue.peekFirst())
            queue.pollFirst();
        return queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
//leetcode submit region end(Prohibit modification and deletion)
