package codetop;

import datastructure.TreeNode;

class lowestCommonAncestorSolution {

    /**
     * 从底往上遍历结点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return null;
    }

    public static void main(String[] args) {
        lowestCommonAncestorSolution solution = new lowestCommonAncestorSolution();
        TreeNode tree = TreeNode.deserialize("3,5,1,6,2,0,8,null,null,7,4", ",", "null");
        System.out.println(solution.lowestCommonAncestor(
                tree,
                tree.left,
                tree.right.left));
    }
}