package leetcode.editor.cn;

import datastructure.TreeNode;

class pathSumOffer2Solution {

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        int res = dfs(root, targetSum);
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }

    private int dfs(TreeNode root, int targetSum) {
        int res = 0;
        if (root == null)
            return 0;
        if (targetSum == root.val)
            res++;
        return res + dfs(root.left, targetSum - root.val) + dfs(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        pathSumOffer2Solution solution = new pathSumOffer2Solution();
        System.out.println(solution.pathSum(TreeNode.deserialize("10,5,-3,3,2,null,11,3,-2,null,1"), 8));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
