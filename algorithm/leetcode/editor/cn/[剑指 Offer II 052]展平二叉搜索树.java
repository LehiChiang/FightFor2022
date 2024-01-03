package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

class increasingBSTSolution {

    private TreeNode resNode;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;
        buildTreeNode(root);
        return dummyNode.right;
    }

    private void buildTreeNode(TreeNode root) {
        if (root == null)
            return;
        buildTreeNode(root.left);
        resNode.right = root;
        root.left = null;
        resNode = root;
        buildTreeNode(root.right);
    }

    public static void main(String[] args) {
        increasingBSTSolution solution = new increasingBSTSolution();
        System.out.println(solution.increasingBST(TreeNode.deserialize("5,3,6,2,4,null,8,1,null,null,null,7,9")));
//        System.out.println(solution.increasingBST(TreeNode.deserialize("5,1,7")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
