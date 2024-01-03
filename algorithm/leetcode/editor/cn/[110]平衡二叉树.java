package leetcode.editor.cn;

//给定一个二叉树，判断它是否是高度平衡的二叉树。
// 本题中，一棵高度平衡二叉树定义为：
// 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
// 示例 1：
//输入：root = [3,9,20,null,null,15,7]
//输出：true
// 示例 2：
//输入：root = [1,2,2,3,3,null,null,4,4]
//输出：false
// 示例 3：
//输入：root = []
//输出：true
// 提示：
// 树中的节点数在范围 [0, 5000] 内 
// -10⁴ <= Node.val <= 10⁴
// Related Topics 树 深度优先搜索 二叉树 👍 812 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

class isBalancedSolution {
    public static void main(String[] args) {
        isBalancedSolution solution = new isBalancedSolution();
//        TreeNode treeNode = TreeNode.deserialize("3,9,20,null,null,15,7");
        TreeNode treeNode = TreeNode.deserialize("3,9,null,10,null,11,null");
        boolean res = solution.isBalanced(treeNode);
        System.out.println(res);
    }

    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    private int depth(TreeNode treeNode) {
        if (treeNode == null)
            return 0;
        int left = depth(treeNode.left);
        if (left == -1) return -1;
        int right = depth(treeNode.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
