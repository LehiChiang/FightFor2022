package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class removeKdigitsSolution {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        Deque<Character> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            char c = num.charAt(i);
            while (!queue.isEmpty() && c < queue.peekLast() && k > 0) {
                queue.pollLast();
                k--;
            }
            queue.offerLast(c);
        }
        for (int i = 0; i < k; i++)
            queue.pollLast();
        StringBuilder stringBuilder = new StringBuilder();
        boolean isLeading = true;
        while (!queue.isEmpty()) {
            char c = queue.pollFirst();
            if (isLeading && c == '0')
                continue;
            isLeading = false;
            stringBuilder.append(c);
        }
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }

    private String getString(String num, int k) {
        if (num.length() == k) return "0";
        StringBuilder s = new StringBuilder(num);
        for (int i = 0; i < k; i++) {
            int idx = 0;
            for (int j = 1; j < s.length() && s.charAt(j) >= s.charAt(j - 1); j++) idx = j;
            s.delete(idx, idx + 1);
            while (s.length() > 1 && s.charAt(0) == '0') s.delete(0, 1);
        }
        return s.toString().equals("") ? "0" : s.toString();
    }

    public static void main(String[] args) {
        removeKdigitsSolution solution = new removeKdigitsSolution();
        System.out.println(solution.removeKdigits("112", 1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
