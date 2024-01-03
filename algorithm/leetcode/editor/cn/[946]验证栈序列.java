package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class validateStackSequencesSolution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> queue = new LinkedList<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            queue.push(pushed[i]);
            while (!queue.isEmpty() && queue.peek() == popped[j]) {
                queue.pop();
                j++;
            }
        }
        return j == popped.length;
    }

    public static void main(String[] args) {
        validateStackSequencesSolution solution = new validateStackSequencesSolution();
        System.out.println(solution.validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
