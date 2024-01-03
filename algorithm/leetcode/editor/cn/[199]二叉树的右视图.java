package leetcode.editor.cn;
//给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
// 示例 1:
//输入: [1,2,3,null,5,null,4]
//输出: [1,3,4]
// 示例 2:
//输入: [1,null,3]
//输出: [1,3]
// 示例 3:
//输入: []
//输出: []
// 提示:
// 二叉树的节点个数的范围是 [0,100] 
// -100 <= Node.val <= 100
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 555 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.ArrayList;
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
class rightSideViewSolution {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null)
            return res;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            while (queueSize > 0) {
                queueSize--;
                TreeNode node = queue.poll();
                if (queueSize == 0)
                    res.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        rightSideViewSolution solution = new rightSideViewSolution();
        TreeNode tree = TreeNode.deserialize("1,2,3,4", ",", "null");
        System.out.println(solution.rightSideView(tree));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
