package codetop;

import datastructure.TreeNode;

class lowestCommonAncestorSolution {

    /**
     * 从底往上遍历结点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == q.val || root.val == p.val) {
            return root;
        }
        TreeNode leftRes = lowestCommonAncestor(root.left, p, q);
        TreeNode rightRes = lowestCommonAncestor(root.right, p, q);
        if (leftRes != null && rightRes != null)
            return root;
        if (leftRes == null) return rightRes;
        if (rightRes == null) return leftRes;
        else return null;
    }

    public static void main(String[] args) {
        lowestCommonAncestorSolution solution = new lowestCommonAncestorSolution();
        TreeNode tree = TreeNode.deserialize("3,5,1,6,2,0,8,null,null,7,4");
        System.out.println(solution.lowestCommonAncestor(
                tree,
                TreeNode.deserialize("5"),
                TreeNode.deserialize("0")).val);
    }
}