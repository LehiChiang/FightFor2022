package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class leastIntervalSolution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        int maxCount = 0, restNum = 0;
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            maxCount = Math.max(maxCount, map.get(c));
        }
        // 难点在于求解restNum;
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            if (entry.getValue() == maxCount)
                restNum += 1;
        return Math.max((maxCount - 1) * (n + 1) + restNum, tasks.length);
    }

    public static void main(String[] args) {
        leastIntervalSolution solution = new leastIntervalSolution();
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A','B', 'C', 'D', 'E', 'F', 'G'}, 2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
