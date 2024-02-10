package leetcode.editor.cn;


//leetcode submit region begin(Prohibit modification and deletion)
class validPalindromeOffer2Solution {
    public static void main(String[] args) {
        validPalindromeOffer2Solution solution = new validPalindromeOffer2Solution();
        System.out.println(solution.validPalindrome("deeee"));
    }

    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int start, int end) {
        int i = start, j = end;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
