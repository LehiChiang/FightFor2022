package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class CheckPermutationSolution {
    public boolean CheckPermutation(String s1, String s2) {
        int[] s1Array = new int[26];
        for (char c : s1.toCharArray())
            s1Array[c - '0']++;
        int[] s2Array = new int[26];
        for (char c : s2.toCharArray())
            s2Array[c - '0']++;
        for (int i = 0; i < 26; i++)
            if (s1Array[i] != s2Array[i])
                return false;
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
