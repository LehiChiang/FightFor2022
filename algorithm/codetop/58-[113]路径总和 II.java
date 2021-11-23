package codetop;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

class pathSumSolution {

    private List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        pathSumSolution solution = new pathSumSolution();
        System.out.println(solution.pathSum(TreeNode.deserialize("1,2,null"), 0));
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum, new ArrayList<>());
        return res;
    }

    private void dfs(TreeNode root, int targetSum, List<Integer> path) {
        if (root == null)
            return;
        path.add(root.val);
        targetSum -= root.val;
        if (targetSum == 0 && root.left == null && root.right == null)
            res.add(new ArrayList<>(path));
        dfs(root.left, targetSum, path);
        dfs(root.right, targetSum, path);
        path.remove(path.size() - 1);
    }
}