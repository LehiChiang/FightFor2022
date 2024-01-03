package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class pruneTreeOffer2Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return root;
        pruneTree(root.left);
        if (!hasOne(root.left))
            root.left = null;
        pruneTree(root.right);
        if (!hasOne(root.right))
            root.right = null;
        if (root.val == 0 && !hasOne(root))
            root = null;
        return root;
    }

    private boolean hasOne(TreeNode root) {
        if (root == null)
            return false;
        return hasOne(root.left) || hasOne(root.right) || root.val == 1;
    }


    public static void main(String[] args) {
        pruneTreeOffer2Solution solution = new pruneTreeOffer2Solution();
        System.out.println(solution.pruneTree(TreeNode.deserialize("0,null,0,0,0")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
