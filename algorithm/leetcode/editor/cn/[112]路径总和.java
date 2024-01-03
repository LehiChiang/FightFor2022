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
        if (root.left == null && root.right == null && targetSum == root.val)
            return true;
        return dfs(root.left, targetSum - root.val) || dfs(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        hasPathSumSolution solution = new hasPathSumSolution();
        System.out.println(solution.hasPathSum(TreeNode.deserialize("1,2,3"), 5));
        System.out.println(solution.hasPathSum(TreeNode.deserialize("5,4,8,11,null,13,4,7,2,null,null,null,1"), 22));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
