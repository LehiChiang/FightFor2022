package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

class convertBSTOffer2Solution {

    private int preSum;

    public static void main(String[] args) {
        convertBSTOffer2Solution solution = new convertBSTOffer2Solution();
        solution.convertBST(
                TreeNode.deserialize("4,1,6,0,2,5,7,null,null,null,3,null,null,null,8")
        ).levelTraverse();
    }

    public TreeNode convertBST(TreeNode root) {
        preSum = 0;
        sumTree(root);
        return root;
    }

//    private void sumTree(TreeNode root) {
//        if (root == null)
//            return;
//        sumTree(root.right);
//        root.val = preSum + root.val;
//        preSum = root.val;
//        sumTree(root.left);
//    }

    private void sumTree(TreeNode root) {
        if (root == null)
            return;
        sumTree(root.right);
        preSum += root.val;
        root.val = preSum;
        sumTree(root.left);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
