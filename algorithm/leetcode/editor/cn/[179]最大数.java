package leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class largestNumberSolution {
    public String largestNumber(int[] nums) {
        List<String> stringList = new ArrayList<>();
        for (int num : nums) {
            stringList.add(String.valueOf(num));
        }
        Collections.sort(stringList, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : stringList) {
            stringBuilder.append(s);
        }
        if(stringBuilder.charAt(0) == '0') return "0";
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        largestNumberSolution solution = new largestNumberSolution();
        System.out.println(solution.largestNumber(new int[]{10, 2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
