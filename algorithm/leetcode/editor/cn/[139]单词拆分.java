package leetcode.editor.cn;//给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
//
// 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
// 
//
// 示例 2： 
//
// 
//输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
//     注意，你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 
//输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 300 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 20 
// s 和 wordDict[i] 仅有小写英文字母组成 
// wordDict 中的所有字符串 互不相同 
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 👍 1320 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class wordBreakSolution {

    private Map<Integer, Boolean> map;
    private Set<String> set;
    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        map = new HashMap<>();
        return dfs(s,0);
    }

    private boolean dfs(String s, int start) {
        if (start == s.length())
            return true;
        if (map.containsKey(start))
            return map.get(start);
        for (int i = start; i < s.length(); i++) {
            if (set.contains(s.substring(start, i + 1)) && dfs(s, i + 1)) {
                map.put(start, true);
                return true;
            }
        }
        map.put(start, false);
        return false;
    }

    private boolean dynamicProgramming(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        wordBreakSolution solution = new wordBreakSolution();
        System.out.println(solution.wordBreak(
                "applepenapple",
                new ArrayList<String>(){{
                    add("apple");
                    add("pen");
                }}));
        System.out.println(solution.wordBreak(
                "catsandog",
                new ArrayList<String>(){{
                    add("cats");
                    add("dog");
                    add("sand");
                    add("and");
                    add("cat");
                }}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
