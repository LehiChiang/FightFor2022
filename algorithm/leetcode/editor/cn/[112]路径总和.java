package leetcode.editor.cn;

//给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和
// targetSum 。
// 叶子节点 是指没有子节点的节点。
// 示例 1：
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//输出：true
// 示例 2：
//输入：root = [1,2,3], targetSum = 5
//输出：false
// 示例 3：
//输入：root = [1,2], targetSum = 0
//输出：false
// 提示：
// 树中节点的数目在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000
// Related Topics 树 深度优先搜索 二叉树 👍 677 👎 0


import com.sun.source.tree.Tree;
import datastructure.TreeNode;

//leetcode submit region begin(Prohibit modification and deletion)
class hasPathSumSolution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum);
    }

    private boolean dfs(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (targetSum == root.val && root.left == null && root.right == null) {
            return true;
        }
        targetSum -= root.val;
        return dfs(root.left, targetSum) || dfs(root.right, targetSum);
    }

    public static void main(String[] args) {
        hasPathSumSolution solution = new hasPathSumSolution();
//        TreeNode root = new TreeNode(5);
//        TreeNode node_4 = new TreeNode(4);
//        TreeNode node_8 = new TreeNode(8);
//        TreeNode node_11 = new TreeNode(11);
//        TreeNode node_13 = new TreeNode(13);
//        TreeNode node__4 = new TreeNode(4);
//        TreeNode node_7 = new TreeNode(7);
//        TreeNode node_2 = new TreeNode(2);
//        TreeNode node_1 = new TreeNode(1);
//        node_11.left = node_7;
//        node_11.right = node_2;
//        node__4.right = node_1;
//        node_4.left = node_11;
//        node_8.left = node_13;
//        node_8.right = node__4;
//        root.left = node_4;
//        root.right = node_8;

        TreeNode root2 = new TreeNode(-2);
        root2.right = new TreeNode(-3);
        System.out.println(solution.hasPathSum(root2, -15));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
