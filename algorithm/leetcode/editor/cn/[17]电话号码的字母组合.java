package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class letterCombinationsSolution {

    private Map<Character, String> map;
    private List<String> res;

    public List<String> letterCombinations(String digits) {
        map = inital();
        res = new ArrayList<>();
        if (digits.length() == 0) {
            res.add("");
            return res;
        }
        dfs(digits, 0, new StringBuilder());
        return res;
    }

    private void dfs(String digits, int index, StringBuilder path) {
        if (path.length() == digits.length()) {
            res.add(path.toString());
            return;
        }
        String mapString = map.get(digits.charAt(index));
        for (int j = 0; j < mapString.length(); j++) {
            path.append(mapString.charAt(j));
            dfs(digits, index + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }

    private Map<Character, String> inital() {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        return map;
    }

    public static void main(String[] args) {
        letterCombinationsSolution solution = new letterCombinationsSolution();
        System.out.println(solution.letterCombinations("23"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
