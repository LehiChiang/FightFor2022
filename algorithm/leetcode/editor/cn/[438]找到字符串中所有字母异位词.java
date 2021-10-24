package leetcode.editor.cn;
//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
// 异位词 指字母相同，但排列不同的字符串。
// 示例 1:
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 示例 2:
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 提示:
// 1 <= s.length, p.length <= 3 * 104 
// s 和 p 仅包含小写字母
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 576 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class findAnagramsSolution {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resList = new ArrayList<>();
        if (s.length() < p.length())
            return resList;
        int[] sFreq = new int[26];
        int[] pFreq = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pFreq[p.charAt(i) - 'a']++;
        }

        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            int curEnd = s.charAt(end) - 'a';
            sFreq[curEnd]++;
            while (sFreq[curEnd] > pFreq[curEnd]) {
                sFreq[s.charAt(start) - 'a']--;
                start++;
            }

            if ((end - start + 1) == p.length())
                resList.add(start);
        }
        return resList;
    }

    public List<Integer> findAnagrams_1(String s, String p) {
        List<Integer> resList = new ArrayList<>();
        int[] pFreq = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pFreq[p.charAt(i) - 'a']++;
        }
        int start = 0, end = -1;
        while (start < s.length()) {
            if (end + 1 < s.length() && (end - start + 1) < p.length()) end++;
            else start++;
            if ((end - start + 1) == p.length() && isAnagrams(s.substring(start, end + 1), pFreq))
                resList.add(start);
        }
        return resList;
    }

    private boolean isAnagrams(String substring, int[] pFreq) {
        int[] winFreq = new int[26];
        for (int j = 0; j < substring.length(); j++) {
            winFreq[substring.charAt(j) - 'a']++;
        }
        return Arrays.equals(winFreq, pFreq);
    }

    public static void main(String[] args) {
        findAnagramsSolution solution = new findAnagramsSolution();
        List<Integer> res = solution.findAnagrams("cbaebabacd", "abc");
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
