package leetcode.editor.cn;//计算给定二叉树的所有左叶子之和。
//
// 示例： 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24 
//
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 352 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import com.sun.source.tree.Tree;
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
class sumOfLeftLeavesSolution {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        int res = 0;
        if (root.left != null && root.left.left == null && root.left.right == null){
            res += root.left.val;
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right) + res;
    }


    public static void main(String[] args) {
        sumOfLeftLeavesSolution solution = new sumOfLeftLeavesSolution();
        TreeNode root = new TreeNode(3);
        TreeNode root_left = new TreeNode(9);
        TreeNode root_right = new TreeNode(20);
        TreeNode root_right_left = new TreeNode(15);
        TreeNode root_right_right = new TreeNode(7);
        root_right.left = root_right_left;
        root_right.right = root_right_right;
        root.left = root_left;
        root.right = root_right;
        System.out.println(root);
        System.out.println(solution.sumOfLeftLeaves(root));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
