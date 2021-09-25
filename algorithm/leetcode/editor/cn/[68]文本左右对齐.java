package leetcode.editor.cn;
//给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
// 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
// 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
// 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
// 说明:
// 单词是指由非空格字符组成的字符序列。 
// 每个单词的长度大于 0，小于等于 maxWidth。 
// 输入单词数组 words 至少包含一个单词。 

// 示例:
// 输入:
//words = ["This", "is", "an", "example", "of", "text", "justification."]
//maxWidth = 16
//输出:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
// 示例 2:
// 输入:
//words = ["What","must","be","acknowledgment","shall","be"]
//maxWidth = 16
//输出:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
//     因为最后一行应为左对齐，而不是左右两端对齐。       
//     第二行同样为左对齐，这是因为这行只包含一个单词。
// 
//
// 示例 3: 
//
// 输入:
//words = ["Science","is","what","we","understand","well","enough","to",
//"explain",
//         "to","a","computer.","Art","is","everything","else","we","do"]
//maxWidth = 20
//输出:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
// 
// Related Topics 字符串 模拟 👍 184 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class fullJustifySolution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; ) {
            list.clear();
            int wordWidth = 0, left = i;
            while (i < words.length && words[i].length() + wordWidth + i - left <= maxWidth) {
                wordWidth += words[i].length();
                list.add(words[i++]);
            }

            if (i == words.length) {
                StringBuilder sb = new StringBuilder();
                for (String word : list) {
                    sb.append(word).append(" ");
                }
                if (sb.length() <= maxWidth)
                    sb.append(" ".repeat(maxWidth - wordWidth - list.size()));
                else
                    sb.deleteCharAt(sb.length() - 1);
                res.add(sb.toString());
                return res;
            }

            int space = maxWidth - wordWidth;
            StringBuilder sb = new StringBuilder();
            if (list.size() == 1) {
                sb.append(list.get(0)).append(" ".repeat(space));
            } else {
                for (int j = 0; j < list.size() - 1; j++) {
                    sb.append(list.get(j));
                    if (j < space % (list.size() - 1))
                        sb.append(" ".repeat(space / (list.size() - 1) + 1));
                    else sb.append(" ".repeat(space / (list.size() - 1)));
                }
                sb.append(list.get(list.size() - 1));
            }
            res.add(sb.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        fullJustifySolution solution = new fullJustifySolution();
        List<String> res = solution.fullJustify(new String[]{"a"},
                2);
        for (String s : res) {
            System.out.println('[' + s + ']');
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
