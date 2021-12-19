package codetop;

//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
// 有效 二叉搜索树定义如下：
// 节点的左子树只包含 小于 当前节点的数。 
// 节点的右子树只包含 大于 当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。
// 示例 1：
//输入：root = [2,1,3]
//输出：true
// 示例 2：
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
// 提示：
// 树中节点数目范围在[1, 10⁴] 内 
// -2³¹ <= Node.val <= 2³¹ - 1
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1325 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

class isValidBSTSolution {

    public boolean isValidBST(TreeNode root) {
        return iterative(root);
    }

    private boolean iterative(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double preOrder = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val < preOrder)
                return false;
            preOrder = root.val;
            root = root.right;
        }
        return true;
    }

    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null)
            return false;
        if (min != null && min.val > root.val)
            return false;
        if (max != null && max.val < root.val)
            return false;
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

    public static void main(String[] args) {
        isValidBSTSolution solution = new isValidBSTSolution();
        System.out.println(solution.isValidBST(TreeNode.deserialize("5,4,6,null,null,3,7")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
