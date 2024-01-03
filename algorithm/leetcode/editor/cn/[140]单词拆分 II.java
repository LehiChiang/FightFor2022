package leetcode.editor.cn;//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的
//句子。 
//
// 说明： 
//
// 
// 分隔时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//输出:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// 示例 2： 
//
// 输入:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//输出:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出:
//[]
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 回溯 👍 548 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class wordBreak2Solution {

    private List<String> res;
    private Set<String> wordSet;
    public List<String> wordBreak(String s, List<String> wordDict) {
        res = new ArrayList<>();
        wordSet= new HashSet<>(wordDict);
        dfs(s, new LinkedList<>(), 0);
        return res;
    }

    private void dfs(String s, Deque<String> path, int start) {
        if (start == s.length()) {
            res.add(String.join(" ", path));
        }

        for (int i = start; i < s.length(); i++) {
            if (wordSet.contains(s.substring(start, i + 1))) {
                path.offerLast(s.substring(start, i + 1));
                dfs(s, path, i + 1);
                path.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        wordBreak2Solution solution = new wordBreak2Solution();
        System.out.println(solution.wordBreak("catsandog", new ArrayList<String>(){{
            add("cat");
            add("cats");
            add("and");
            add("sand");
            add("dog");
        }}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
