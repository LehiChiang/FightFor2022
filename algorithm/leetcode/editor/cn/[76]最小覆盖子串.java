package leetcode.editor.cn;//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 10⁵ 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 👍 1427 👎 0


import com.sun.source.tree.IfTree;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class minWindowSolution {

//    private HashMap<Character, Integer> hashMap;
//    public String minWindow(String s, String t) {
//        int tLen = t.length(), sLen = s.length();
//        boolean startFlag = false;
//        hashMap = new HashMap<>();
//        initTHashMapState(t);
//        int end = -1, start = 0, minLen = Integer.MAX_VALUE;
//        int ansStart = -1, ansEnd = -1;
//        while (end < sLen) {
//            end++;
//            if (end < sLen && hashMap.containsKey(s.charAt(end))) {
//                if (!startFlag) {
//                    startFlag = true;
//                    start = end;
//                }
//                hashMap.put(s.charAt(end), hashMap.get(s.charAt(end)) - 1);
//            }
//            if (checkTHashMapState()) {
//                if (end - start + 1 < minLen) {
//                    minLen = end - start + 1;
//                    ansStart = start;
//                    ansEnd = end;
//                }
//                initTHashMapState(t);
//                startFlag = false;
//            }
//        }
//        return ansStart == -1 ? "" : s.substring(ansStart, ansEnd + 1);
//    }
//
//    private void initTHashMapState(String t) {
//        for (char c : t.toCharArray()) {
//            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
//        }
//    }
//
//    private boolean checkTHashMapState() {
//        for (Map.Entry<Character, Integer> entry: hashMap.entrySet())
//            if (entry.getValue() != 0)
//                return false;
//        return true;
//    }

    private HashMap<Character, Integer> tStateMap;
    private HashMap<Character, Integer> winStateMap;

    public String minWindow(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        tStateMap = new HashMap<>();
        winStateMap = new HashMap<>();
        for (Character c : t.toCharArray())
            tStateMap.put(c, tStateMap.getOrDefault(c, 0) + 1);
        int end = -1, start = 0, minLen = Integer.MAX_VALUE;
        int ansEnd = -1, ansStart = -1;
        while (end < sLen) {
            // 开始扩张右边界
            end++;
            if (end < sLen && tStateMap.containsKey(s.charAt(end))) {
                winStateMap.put(s.charAt(end), winStateMap.getOrDefault(s.charAt(end), 0) + 1);
            }
            // 不断地将右边界指向的元素加入到winStateMap中去，直到t中的所有字符都在winStateMap中，则开始
            // 缩小左边界！

            // check()是判断t中的所有字符都在winStateMap中有记录？返回true的话说明当前窗口里所有数值已经都包括
            // 了t中的所有字符,那么就开始选择最小的覆盖子串。返回true的话说明当前窗口里所有数值没有包含t串的所有
            // 字符还需要往里添加。

            while (checkTInWinMap() && start <= end) {
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    ansStart = start;
                    ansEnd = end;
                }

                // 开始缩小左边界，在这个while循环里，保证右边界不发生缩放
                if (tStateMap.containsKey(s.charAt(start))) {
                    winStateMap.put(s.charAt(start), winStateMap.getOrDefault(s.charAt(start), 0) - 1);
                }
                start++;
            }
        }
        return ansStart == -1 ? "" : s.substring(ansStart, ansEnd + 1);
    }

    private boolean checkTInWinMap() {
        for (Map.Entry<Character, Integer> entry : tStateMap.entrySet()) {
            if (winStateMap.getOrDefault(entry.getKey(), 0) < entry.getValue())
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        minWindowSolution solution = new minWindowSolution();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
