package leetcode.editor.cn;


//leetcode submit region begin(Prohibit modification and deletion)
class checkInclusionOffer2Solution {
    public static void main(String[] args) {
        checkInclusionOffer2Solution solution = new checkInclusionOffer2Solution();
        System.out.println(solution.checkInclusion("ab", "eidbaooo"));
    }

    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[] win = new int[26];
        for (int i = 0; i < m; i++)
            --win[s1.charAt(i) - 'a'];
        int left = 0;
        for (int right = 0; right < n; right++) {
            ++win[s2.charAt(right) - 'a'];
            while (win[s2.charAt(right) - 'a'] > 0) {
                --win[s2.charAt(left) - 'a'];
                left++;
            }
            if (right - left + 1 == m)
                return true;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
