package leetcode.editor.cn;

//给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，
// 该字符串可以通过删除 s 中的某些字符得到。如果答案不止一个，返回长度最长且字典序最小的字符串。
// 如果答案不存在，则返回空字符串。
//
// 示例 1：
//输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
//输出："apple"
//
// 示例 2：
//输入：s = "abpcplea", dictionary = ["a","b","c"]
//输出："a"
//
// 提示：
// 1 <= s.length <= 1000 
// 1 <= dictionary.length <= 1000 
// 1 <= dictionary[i].length <= 1000 
// s 和 dictionary[i] 仅由小写英文字母组成 
// 
// Related Topics 数组 双指针 字符串 排序 👍 179 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class findLongestWordSolution {
//    public String findLongestWord(String s, List<String> dictionary) {
//        String res = "";
//        for (String word : dictionary) {
//            int i = 0, j = 0;
//            while (i < s.length() && j < word.length()) {
//                if (s.charAt(i) == word.charAt(j)) {
//                    j++;
//                }
//                i++;
//            }
//            if (j == word.length()) {
//                if (word.length() > res.length() || (word.length() == res.length() && word.compareTo(res) < 0))
//                    res = word;
//            }
//        }
//        return res;
//    }

    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary, (s1, t1) -> {
            if (s1.length() == t1.length()) {
                return s1.compareTo(t1);
            } else {
                return t1.length() - s1.length();
            }
        });

        for (String word : dictionary) {
            int i = 0, j = 0;
            while (i < s.length() && j < word.length()) {
                if (s.charAt(i) == word.charAt(j)) {
                    j++;
                }
                i++;
            }
            if (j == word.length()) {
                return word;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        findLongestWordSolution solution = new findLongestWordSolution();
        List<String> list = new ArrayList<>() {{
            add("a");
            add("b");
            add("c");
            add("d");
        }};
        System.out.println(solution.findLongestWord("abpcplea", list));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
