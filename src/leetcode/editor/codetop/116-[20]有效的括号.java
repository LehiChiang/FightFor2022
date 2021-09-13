package leetcode.editor.codetop;

import java.util.Stack;

class isValidSolution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            while (!stack.isEmpty()) {
                if (stack.pop() == c)
                    continue;
                else return false;
            }
        }
        return stack.isEmpty();
    }
}
