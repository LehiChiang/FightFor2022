package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class isAlienSortedOffer2Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++)
            map.put(order.charAt(i), i);
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int j;
            for (j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    if (map.get(word1.charAt(j)) > map.get(word2.charAt(j)))
                        return false;
                    else
                        break;
                }
            }
            if (word2.length() < word1.length())
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        isAlienSortedOffer2Solution solution = new isAlienSortedOffer2Solution();
        System.out.println(solution.isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
