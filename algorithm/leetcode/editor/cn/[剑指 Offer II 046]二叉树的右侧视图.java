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
class rightSideViewOffer2Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            list.add(queue.peek().val);
            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                if (node.right != null)
                    queue.offer(node.right);
                if (node.left != null)
                    queue.offer(node.left);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        rightSideViewOffer2Solution solution = new rightSideViewOffer2Solution();
        System.out.println(solution.rightSideView(TreeNode.deserialize("1,2,3,null,5,null,4")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
