package leetcode.editor.cn;//给定一个二叉树，找出其最大深度。
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1026 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import com.sun.source.tree.Tree;
import datastructure.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class maxDepthSolution {

    /**
     * 递归写法
     *
     * @param root
     * @return
     */
    public int maxDepthRecur(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * 迭代写法
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            for (int qSize = queue.size(); qSize > 0; qSize--) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            level++;
        }
        return level;
    }

    public static void main(String[] args) {
        maxDepthSolution solution = new maxDepthSolution();
        TreeNode tree1 = TreeNode.deserialize("3,9,20,null,null,15,7");
        TreeNode tree2 = new TreeNode(
                1,
                new TreeNode(
                        2,
                        new TreeNode(
                                3,
                                new TreeNode(
                                        4,
                                        new TreeNode(
                                                5
                                        ),
                                        null),
                                null),
                        null),
                null);
        int res = solution.maxDepth(tree2);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
