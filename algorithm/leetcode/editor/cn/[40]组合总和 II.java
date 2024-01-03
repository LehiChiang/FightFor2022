package leetcode.editor.cn;

//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
// candidates 中的每个数字在每个组合中只能使用一次。
// 注意：解集不能包含重复的组合。
// 示例 1:
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[[1,1,6],[1,2,5],[1,7],[2,6]]
//
// 示例 2:
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[[1,2,2],[5]]
//
// 提示:
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30
// Related Topics 数组 回溯 👍 766 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class combinationSum2Solution {

    List<List<Integer>> res;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, new LinkedList<Integer>(), 0, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, LinkedList<Integer> path, int index, int sum) {
        if (index > candidates.length || sum > target)
            return;
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target)
                continue;
            if (i > index && candidates[i] == candidates[i - 1])
                continue;
            path.addLast(candidates[i]);
            dfs(candidates, target, path, i + 1, sum + candidates[i]);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        combinationSum2Solution solution = new combinationSum2Solution();
        System.out.println(solution.combinationSum2(new int[]{10,1,2,7,6,5,1}, 8));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
