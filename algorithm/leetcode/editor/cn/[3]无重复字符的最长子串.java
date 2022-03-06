package leetcode.editor.cn;
//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
// 示例 1:
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 示例 2:
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 示例 3:
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
//    请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
// 示例 4:
//输入: s = ""
//输出: 0
// 提示：
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 5849 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class lengthOfLongestSubstringSolution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int right = 0, res = 0, left = 0;
        while (right < s.length()){
            if (right != 0)
                set.remove(s.charAt(left++));
            while (right < s.length() && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        lengthOfLongestSubstringSolution solution = new lengthOfLongestSubstringSolution();
        int res = solution.lengthOfLongestSubstring("bbbbb");
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
