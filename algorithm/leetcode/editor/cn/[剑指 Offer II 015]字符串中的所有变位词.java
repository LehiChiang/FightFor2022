package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class findAnagramsOffer2Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int[] sWin = new int[26];
        int[] pwin = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pwin[p.charAt(i) - 'a']++;
        }
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            sWin[s.charAt(end) - 'a']++;
            while (sWin[s.charAt(end) - 'a'] > pwin[s.charAt(end) - 'a']) {
                sWin[s.charAt(start) - 'a']--;
                start++;
            }
            if (end - start + 1 == p.length())
                list.add(start);
        }
        return list;
    }

    public static void main(String[] args) {
        findAnagramsOffer2Solution solution = new findAnagramsOffer2Solution();
        System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
