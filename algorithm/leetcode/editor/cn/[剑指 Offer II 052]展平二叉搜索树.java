package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

class increasingBSTSolution {

    private TreeNode resNode;

    public static void main(String[] args) {
        increasingBSTSolution solution = new increasingBSTSolution();
        System.out.println(solution.increasingBST(TreeNode.deserialize("5,3,6,2,4,null,8,1,null,null,null,7,9")));
//        System.out.println(solution.increasingBST(TreeNode.deserialize("5,1,7")));
    }
//    public TreeNode increasingBST(TreeNode root) {
//        TreeNode dummyNode = new TreeNode(-1);
//        resNode = dummyNode;
//        buildTreeNode(root);
//        return dummyNode.right;
//    }
//
//    private void buildTreeNode(TreeNode root) {
//        if (root == null)
//            return;
//        buildTreeNode(root.left);
//        resNode.right = root;
//        root.left = null;
//        resNode = root;
//        buildTreeNode(root.right);
//    }

    public TreeNode increasingBST(TreeNode root) {
        resNode = new TreeNode(-1);
        TreeNode dummyNode = resNode;
        dfs(root);
        return dummyNode.right;
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null)
            return null;
        dfs(root.left);
        resNode.right = root;
        root.left = null;
        resNode = root;
        dfs(root.right);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
