package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class groupAnagramsOffer2Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        for (String word : strs) {
            int key = 0;
            for (int i = 0; i < word.length(); i++) {
                key = key | (1 << (word.charAt(i) - 'a'));
            }
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(word);
            map.put(key, list);
        }
        for (Map.Entry<Integer, List<String>> entry : map.entrySet())
            res.add(entry.getValue());
        return res;
    }

    public static void main(String[] args) {
        groupAnagramsOffer2Solution solution = new groupAnagramsOffer2Solution();
        System.out.println(solution.groupAnagrams(new String[]{""}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
