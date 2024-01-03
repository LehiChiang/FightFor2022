package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;
import datastructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class inorderSuccessorOffer2Solution {
    public TreeNode myInorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> queue = new LinkedList<>();
        TreeNode curNode = null;
        while(!queue.isEmpty() || root != null) {
            while (root != null) {
                queue.offerLast(root);
                root = root.left;
            }
            TreeNode node = queue.pollLast();
            if (curNode != null)
                return node;
            if (node.val == p.val) {
                if (node.right == null)
                    return queue.pollLast();
                else curNode = p;
            }
            root = node.right;
        }
        return null;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode cur = root;
        TreeNode res = null;
        while (cur != null) {
           if (cur.val > p.val) {
               res = cur;
               cur = cur.left;
           } else {
               cur = cur.right;
           }
        }
        return res;
    }

    public static void main(String[] args) {
        inorderSuccessorOffer2Solution solution = new inorderSuccessorOffer2Solution();
        TreeNode node = TreeNode.deserialize("6,2,8,0,4,7,9,null,null,3,5");
        System.out.println(solution.inorderSuccessor(node, node.left));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
