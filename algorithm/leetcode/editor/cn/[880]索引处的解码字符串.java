package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class decodeAtIndexSolution {
    public String decodeAtIndex(String s, int k) {
        long length = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)))
                length++;
            else
                length *= s.charAt(i) - '0';
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            k %= length;
            if (k == 0 && Character.isLetter(c))
                return String.valueOf(c);
            if (Character.isDigit(c))
                length /= c - '0';
            else
                length--;
        }
        return "";
    }

    public static void main(String[] args) {
        decodeAtIndexSolution solution = new decodeAtIndexSolution();
        System.out.println(solution.decodeAtIndex("ha22", 5));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
