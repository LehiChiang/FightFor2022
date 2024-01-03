package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class MyStack {

    private Deque<Integer> queue;
    public MyStack() {
        queue = new LinkedList<>();
    }
    
    public void push(int x) {
        queue.offerLast(x);
    }
    
    public int pop() {
        return queue.pollFirst();
    }
    
    public int top() {
        return queue.peekFirst();
    }
    
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)
