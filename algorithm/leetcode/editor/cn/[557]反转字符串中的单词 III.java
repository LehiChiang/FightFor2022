package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class reverseWords3Solution {
    public String reverseWords(String s) {
        int left = 0, right = 0;
        char[] schar = s.toCharArray();
        while (right < schar.length) {
            if (schar[right] == ' ') {
                reverseString(schar, left, right - 1);
                right++;
                left = right;
            } else {
                right++;
            }
        }
        reverseString(schar, left, s.length() - 1);
        return new String(schar);
    }

    private void reverseString(char[] schar, int left, int right) {
        while (left < right) {
            char c = schar[left];
            schar[left] = schar[right];
            schar[right] = c;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        reverseWords3Solution solution = new reverseWords3Solution();
        System.out.println(solution.reverseWords("Let's take LeetCode contest"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
