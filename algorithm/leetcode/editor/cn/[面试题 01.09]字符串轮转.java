package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class isFlipedStringSolution {
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        String s = s2 + s2;
        return s.contains(s1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
