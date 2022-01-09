package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

class lowestCommonAncestorBinaryTreeSolution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val == p.val || root.val == q.val)
            return root;
        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
        else
            return root;
    }

    public static void main(String[] args) {
        lowestCommonAncestorBinaryTreeSolution solution = new lowestCommonAncestorBinaryTreeSolution();
        TreeNode root = TreeNode.deserialize("6,2,8,0,4,7,9,null,null,3,5");
        System.out.println(solution.lowestCommonAncestor(root, root.left, root.right));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
