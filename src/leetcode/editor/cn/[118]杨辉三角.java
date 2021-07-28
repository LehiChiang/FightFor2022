package leetcode.editor.cn;
//给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
// 示例 1:
//输入: numRows = 5
//输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// 示例 2:
//输入: numRows = 1
//输出: [[1]]
// 提示:
// 1 <= numRows <= 30
// Related Topics 数组 动态规划 
// 👍 533 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Yanghuisanjiao1Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0 ; i < numRows ; i++) {
             temp.add(0, 1);
             for (int j = 1 ; j < i ; j++) {
                 temp.set(j, temp.get(j) + temp.get(j + 1));
             }
             res.add(new ArrayList<>(temp));
        }
        return res;
    }

    public static void main(String[] args) {
        Yanghuisanjiao1Solution solution = new Yanghuisanjiao1Solution();
        List<List<Integer>> res = solution.generate(15);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
