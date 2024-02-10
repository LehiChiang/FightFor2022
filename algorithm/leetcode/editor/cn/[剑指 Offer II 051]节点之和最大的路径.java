package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

class maxPathSumOffer2Solution {

    private int maxPathSum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        maxPathSumOffer2Solution solution = new maxPathSumOffer2Solution();
        System.out.println(solution.maxPathSum(TreeNode.deserialize("1,-2,3")));
    }

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxPathSum;
    }

    private int myDFS(TreeNode root) {
        if (root == null)
            return 0;
        int leftSum = myDFS(root.left);
        int rightSum = myDFS(root.right);
        maxPathSum = Math.max(root.val, Math.max(leftSum + root.val + rightSum, maxPathSum));
        return Math.max(0, root.val + Math.max(leftSum, rightSum));
    }

    private int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int leftSum = Math.max(dfs(root.left), 0);
        int rightSum = Math.max(dfs(root.right), 0);
        maxPathSum = Math.max(leftSum + root.val + rightSum, maxPathSum);
        return root.val + Math.max(leftSum, rightSum);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
