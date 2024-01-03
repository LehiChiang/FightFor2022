package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class reorganizeStringSolution {

    public String reorganizeString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            maxCount = Math.max(maxCount, map.get(s.charAt(i)));
        }
        if (maxCount > (s.length() + 1) / 2)
            return "";
        PriorityQueue<Character> queue = new PriorityQueue<>((o1, o2) -> map.get(o2) - map.get(o1));
        for (Character key : map.keySet()) {
            queue.offer(key);
        }
        StringBuilder sb = new StringBuilder();
        while (queue.size() > 1) {
            char a = queue.poll();
            char b = queue.poll();
            sb.append(a).append(b);
            if (map.get(a) - 1 != 0) {
                map.put(a, map.get(a) - 1);
                queue.offer(a);
            }
            if (map.get(b) - 1 != 0) {
                map.put(b, map.get(b) - 1);
                queue.offer(b);
            }
        }
        if (queue.size() == 1)
            sb.append(queue.poll());
        return sb.toString();
    }

    public static void main(String[] args) {
        reorganizeStringSolution solution = new reorganizeStringSolution();
        System.out.println(solution.reorganizeString("aaabbc"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
