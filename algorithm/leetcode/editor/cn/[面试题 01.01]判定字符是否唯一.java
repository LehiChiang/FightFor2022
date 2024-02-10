package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class IsUniqueSolution {
    public static boolean isUnique(String astr) {
        int bitmap = 0;
        for (int i = 0; i < astr.length(); i++) {
            int index = astr.charAt(i) - 'a';
            int bit = 1 << index;
            if ((bitmap & bit) != 0)
                return false;
            bitmap = bitmap | bit;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
