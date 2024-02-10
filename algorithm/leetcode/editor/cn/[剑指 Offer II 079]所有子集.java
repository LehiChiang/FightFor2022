package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class subsetsOffer2Solution {

    private List<List<Integer>> list;

    public static void main(String[] args) {
        subsetsOffer2Solution solution = new subsetsOffer2Solution();
        System.out.println(solution.subsets(new int[]{1, 2, 3}));
    }

    private void dfs(int[] nums, int index, List<Integer> path) {
        if (!list.contains(path)) {
            list.add(new ArrayList<>(path));
        }
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path);
            path.remove(path.size() - 1);
        }
    }
//        if (!list.contains(path)) {
//            list.add(new ArrayList<>(path));
//        }
//        for (int i = index; i < nums.length; i++) {
//            path.add(nums[i]);
//            dfs(nums, i + 1, path);
//            path.remove(path.size() - 1);
//        }
//    }

    public List<List<Integer>> subsets(int[] nums) {
        list = new ArrayList<>();
        list.add(new ArrayList<>());
        dfs(nums, 0, new LinkedList<>());
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
