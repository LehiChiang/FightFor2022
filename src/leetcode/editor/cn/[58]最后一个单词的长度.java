package leetcode.editor.cn;
//给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
//
// 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "Hello World"
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：s = "   fly me   to   the moon  "
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：s = "luffy is still joyboy"
//输出：6
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅有英文字母和空格 ' ' 组成 
// s 中至少存在一个单词 
// 
// Related Topics 字符串 👍 377 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class lengthOfLastWordSolution {
    public int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        int res = 0;
        while (index >= 0 && s.charAt(index) == ' ') {
            index--;
        }

        while (index >= 0 && s.charAt(index) != ' ') {
            res++;
            index--;
        }
        return res;
    }

    public static void main(String[] args) {
        lengthOfLastWordSolution solution = new lengthOfLastWordSolution();
        System.out.println(solution.lengthOfLastWord("   "));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
