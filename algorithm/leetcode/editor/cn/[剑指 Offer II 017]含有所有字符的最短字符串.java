package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;


//leetcode submit region begin(Prohibit modification and deletion)
class minWindowOffer2Solution {

    private Map<Character, Integer> tState;
    private Map<Character, Integer> winState;

    public static void main(String[] args) {
        minWindowOffer2Solution solution = new minWindowOffer2Solution();
        System.out.println(solution.minWindow("a", "aa"));
    }

    public String minWindow(String s, String t) {
        tState = new HashMap<>();
        winState = new HashMap<>();
        for (int i = 0; i < t.length(); i++)
            tState.put(t.charAt(i), tState.getOrDefault(t.charAt(i), 0) + 1);
        int left = 0, right = -1;
        int ansStart = 0, ansEnd = 0, minLen = s.length();
        while (right < s.length()) {
            ++right;
            if (right < s.length() && tState.containsKey(s.charAt(right))) {
                winState.put(s.charAt(right), winState.getOrDefault(s.charAt(right), 0) + 1);
            }
            while (checkState() && left <= right) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    ansEnd = right;
                    ansStart = left;
                }
                if (tState.containsKey(s.charAt(left)))
                    winState.put(s.charAt(left), winState.getOrDefault(s.charAt(left), 0) - 1);
                left++;
            }

        }
        return ansStart == 0 ? "" : s.substring(ansStart, ansEnd + 1);
    }

    private boolean checkState() {
        for (Map.Entry<Character, Integer> entry : tState.entrySet()) {
            if (entry.getValue() > winState.getOrDefault(entry.getKey(), 0))
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
