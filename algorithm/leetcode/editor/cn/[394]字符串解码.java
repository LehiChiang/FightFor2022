package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class decodeStringSolution {
    public String decodeString(String s) {
        Deque<Integer> nStack = new LinkedList<>();
        Deque<String> sStack = new LinkedList<>(){{add("");}};
        for (int i = 0; i < s.length(); i++) {
            while (i < s.length() && Character.isLetter(s.charAt(i)))
                sStack.push(sStack.pop() + s.charAt(i++));
            if (i < s.length() && Character.isDigit(s.charAt(i))) {
                int k = 0;
                while (Character.isDigit(s.charAt(i)))
                    k = k * 10 + s.charAt(i++) - '0';
                nStack.push(k);
            }
            if (i < s.length() && s.charAt(i) == '[')
                sStack.push("");
            if (i < s.length() && s.charAt(i) == ']') {
                String popedStr = sStack.pop();
                sStack.push(sStack.pop() + popedStr.repeat(nStack.pop()));
            }
        }
        return sStack.pop();
    }

    public static void main(String[] args) {
        decodeStringSolution solution = new decodeStringSolution();
        System.out.println(solution.decodeString("10[abc]"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
