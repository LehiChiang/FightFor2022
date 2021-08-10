package leetcode.editor.cn;
//给定一个字符串数组，将字母异位词组合在一起。可以按任意顺序返回结果列表。
// 字母异位词指字母相同，但排列不同的字符串。
// 示例 1:
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
// 示例 2:
//输入: strs = [""]
//输出: [[""]]
// 示例 3:
//输入: strs = ["a"]
//输出: [["a"]]
// 提示：
// 1 <= strs.length <= 104 
// 0 <= strs[i].length <= 100 
// strs[i] 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 排序 
// 👍 804 👎 0


import java.util.*;


//leetcode submit region begin(Prohibit modification and deletion)
class groupAnagramsSolution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        char[] cl;
        for (String word : strs){
            cl = word.toCharArray();
            Arrays.sort(cl);
            String key = new String(cl);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(word);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        groupAnagramsSolution solution = new groupAnagramsSolution();
        List<List<String>> res = solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
