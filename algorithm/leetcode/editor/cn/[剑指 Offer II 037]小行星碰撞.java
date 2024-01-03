package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class asteroidCollisionOffer2Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        step : for (int asteroid : asteroids) {
            while (!stack.isEmpty() && stack.peekLast() > 0 && asteroid < 0) {
                if (stack.peekLast() < - asteroid)
                    stack.pollLast();
                else if (stack.peekLast() == -asteroid) {
                    stack.pollLast();
                    continue step;
                }
                else continue step;
            }
            stack.offerLast(asteroid);
        }
        int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = stack.pollFirst();
        return res;
    }

    public static void main(String[] args) {
        asteroidCollisionOffer2Solution solution = new asteroidCollisionOffer2Solution();
        System.out.println(Arrays.toString(solution.asteroidCollision(new int[]{-2,-1,1,2})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
