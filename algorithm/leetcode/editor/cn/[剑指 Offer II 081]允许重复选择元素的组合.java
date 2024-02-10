package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class combinationSumOffer2Solution {

    private List<List<Integer>> res;

    public static void main(String[] args) {
        combinationSumOffer2Solution solution = new combinationSumOffer2Solution();
        System.out.println(solution.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        dfs(candidates, target, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int[] candidates, int target, int index, LinkedList<Integer> path) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (target - candidates[i] < 0)
                continue;
            path.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, path);
            path.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
