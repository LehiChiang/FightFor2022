package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class combineOffer2Solution {

    private List<List<Integer>> list;

    public static void main(String[] args) {
        combineOffer2Solution solution = new combineOffer2Solution();
        System.out.println(solution.combine(4, 2));
    }

    public List<List<Integer>> combine(int n, int k) {
        list = new ArrayList<>();
        dfs(n, k, 1, new ArrayList<>());
        return list;
    }

    private void dfs(int n, int k, int index, ArrayList<Integer> path) {
        if (path.size() == k) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i <= n; i++) {
            path.add(i);
            dfs(n, k, i + 1, path);
            path.remove(path.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
