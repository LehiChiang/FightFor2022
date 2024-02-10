package leetcode.editor.cn;


//leetcode submit region begin(Prohibit modification and deletion)
class isPalindromeOffer2Solution {
    public static void main(String[] args) {
        isPalindromeOffer2Solution solution = new isPalindromeOffer2Solution();
        System.out.println(solution.isPalindrome(".,"));
    }

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
                left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
                right--;
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                    return false;
                else {
                    left++;
                    right--;
                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
