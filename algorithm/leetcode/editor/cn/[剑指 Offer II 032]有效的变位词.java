package leetcode.editor.cn;

import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class isAnagramOffer2Solution {
    public static void main(String[] args) {
        isAnagramOffer2Solution solution = new isAnagramOffer2Solution();
        System.out.println(solution.isAnagram("a", "a"));
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int j = 0; j < t.length(); j++) {
            char c = t.charAt(j);
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) < 0)
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
