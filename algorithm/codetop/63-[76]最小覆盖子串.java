package codetop;

import java.util.HashMap;
import java.util.Map;

class minWindowSolution {

    HashMap<Character, Integer> tStateMap;
    HashMap<Character, Integer> winStateMap;

    public static void main(String[] args) {
        minWindowSolution solution = new minWindowSolution();
        String res = solution.minWindow("a", "a");
        System.out.println(res);
    }

    public String minWindow(String s, String t) {
        tStateMap = new HashMap<>();
        winStateMap = new HashMap<>();
        for (Character c : t.toCharArray())
            tStateMap.put(c, tStateMap.getOrDefault(c, 0) + 1);
        int end = -1, start = 0, minLen = Integer.MAX_VALUE;
        int ansStart = -1, ansEnd = -1;
        while (end < s.length()) {
            ++end;
            if (end < s.length() && tStateMap.containsKey(s.charAt(end))) {
                winStateMap.put(s.charAt(end), winStateMap.getOrDefault(s.charAt(end), 0) + 1);
            }

            while (checkWinIsFull() && start <= end) { //错误：忘记判断start和end的大小
                if (end - start + 1 < minLen) {
                    ansStart = start;
                    ansEnd = end;
                    minLen = ansEnd - ansStart + 1;
                }

                // 错误：忘了加判断条件
                if (tStateMap.containsKey(s.charAt(start))) {
                    winStateMap.put(s.charAt(start), winStateMap.getOrDefault(s.charAt(start), 0) - 1);
                }
                start++;
            }
        }
        return ansStart == -1 ? "" : s.substring(ansStart, ansEnd + 1); // 错误：忘了判断特殊条件
    }

    private boolean checkWinIsFull() {
        for (Map.Entry<Character, Integer> entry : tStateMap.entrySet()) {
            if (entry.getValue() > winStateMap.getOrDefault(entry.getKey(), 0))
                return false;
        }
        return true;
    }
}
