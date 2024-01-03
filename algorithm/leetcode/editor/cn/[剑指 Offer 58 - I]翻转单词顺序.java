package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class reverseWordsOfferSolution {
    public String reverseWords(String s) {
        s = s.trim();
        int end = s.length() - 1, start = end;
        StringBuilder stringBuilder = new StringBuilder();
        while (start >= 0) {
            while (start >= 0 && s.charAt(start) != ' ') start--;
            stringBuilder.append(s, start + 1, end + 1).append(' ');
            while (start >= 0 && s.charAt(start) == ' ') start--;
            end = start;
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        reverseWordsOfferSolution solution = new reverseWordsOfferSolution();
        System.out.println(solution.reverseWords("hello my    name is lehi"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
