package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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
class findBottomLeftValueSolution {
    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int val = 0;
        while (!queue.isEmpty()) {
            val = queue.peek().val;
            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
        return val;
    }

    public static void main(String[] args) {
        findBottomLeftValueSolution solution = new findBottomLeftValueSolution();
        System.out.println(solution.findBottomLeftValue(TreeNode.deserialize("1,2,3,4,null,5,6,null,null,7,null")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
