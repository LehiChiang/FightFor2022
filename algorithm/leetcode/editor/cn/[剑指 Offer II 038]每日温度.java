package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;


//leetcode submit region begin(Prohibit modification and deletion)
class dailyTemperaturesOffer2Solution {
    public static void main(String[] args) {
        dailyTemperaturesOffer2Solution solution = new dailyTemperaturesOffer2Solution();
        System.out.println(Arrays.toString(solution.dailyTemperatures(new int[]{30, 60, 90})));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
