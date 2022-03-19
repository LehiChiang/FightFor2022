package leetcode.editor.cn;

import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class canPermutePalindromeSolution {
    public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> dic = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            dic.put(s.charAt(i), dic.getOrDefault(s.charAt(i), 0) + 1);
        }
        int odd = 0;
        for (int val : dic.values()) {
            if (val % 2 == 1) {
                if (++odd > 1)
                    return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
