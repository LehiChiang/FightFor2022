package leetcode.editor.cn;

//给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中
// 其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，
// 则返回空字符串。
//
// 示例 1：
// 输入：
//words = ["w","wo","wor","worl", "world"]
//输出："world"
//解释： 
//单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
//
// 示例 2：
// 输入：
//words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
//输出："apple"
//解释：
//"apply"和"apple"都能由词典中的单词组成。但是"apple"的字典序小于"apply"。
//
// 提示：
// 所有输入的字符串都只包含小写字母。 
// words数组长度范围为[1,1000]。 
// words[i]的长度范围为[1,30]。 
// 
// Related Topics 字典树 数组 哈希表 字符串 排序 👍 156 👎 0


import java.util.Arrays;
import java.util.HashSet;


//leetcode submit region begin(Prohibit modification and deletion)
class longestWordSolution {
    public String longestWord(String[] words) {
        Arrays.sort(words);
        HashSet<String> set = new HashSet<>();
        String res = "";
        for (String word : words) {
            if (word.length() == 1 || set.contains(word.substring(0, word.length() - 1))) {
                res = word.length() > res.length() ? word : res;
                set.add(word);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        longestWordSolution solution = new longestWordSolution();
        System.out.println(solution.longestWord(new String[]{"m","mo","moc","moch","mocha","l","la","lat","latt","latte","c","ca","cat"}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
