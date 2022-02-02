package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class permuteUniqueOffer2Solution {

    private List<List<Integer>> res;
    private boolean[] visited;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, new LinkedList<>());
        return res;
    }

    private void dfs(int[] nums, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                continue;
            path.add(nums[i]);
            visited[i] = true;
            dfs(nums, path);
            visited[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        permuteUniqueOffer2Solution solution = new permuteUniqueOffer2Solution();
        System.out.println(solution.permuteUnique(new int[]{1,1,2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
