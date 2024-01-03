package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class combinationSum2Offer2Solution {

    private List<List<Integer>> res;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates);
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
                break;
            if (i > index && candidates[i] == candidates[i - 1])
                continue;
            path.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1, path);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        combinationSum2Offer2Solution solution = new combinationSum2Offer2Solution();
        System.out.println(solution.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
