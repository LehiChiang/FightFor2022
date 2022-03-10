package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class checkInclusionSolution {
    public boolean checkInclusion(String s1, String s2) {
         int[] win = new int[26];
         for (int i = 0; i < s1.length(); i++) {
             win[s1.charAt(i) - 'a']++;
         }
         int start = 0, end = 0;
         while (end < s2.length()) {
             win[s2.charAt(end) - 'a']--;
             while (win[s2.charAt(end) - 'a'] < 0) {
                 win[s2.charAt(start) - 'a']++;
                 start++;
             }
             if (end - start + 1 == s1.length()) {
                 return true;
             }
             end++;
         }
         return false;
    }

    public static void main(String[] args) {
        checkInclusionSolution solution = new checkInclusionSolution();
        System.out.println(solution.checkInclusion("ab", "eidbaooo"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
