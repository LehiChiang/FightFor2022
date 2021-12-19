package leetcode.editor.cn;
//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
// 说明：
// 所有数字都是正整数。 
// 解集不能包含重复的组合。
// 示例 1:
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
// 示例 2:
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
// Related Topics 数组 回溯 👍 398 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class combinationSum3Solution {

    private List<List<Integer>> res;
    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        dfs(k, n, new LinkedList<Integer>(), 1, 0);
        return res;
    }

    private void dfs(int k, int n, LinkedList<Integer> path, int index, int sum) {
        if (sum == n && path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i <= 9; i++) {
            if (sum + i > n)
                break;
            path.addLast(i);
            dfs(k, n, path, i + 1, sum + i);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        combinationSum3Solution solution = new combinationSum3Solution();
        System.out.println(solution.combinationSum3(3, 9));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
