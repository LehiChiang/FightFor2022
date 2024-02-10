package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

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
class BSTIteratorOffer2 {

    private Deque<TreeNode> queue;
    private TreeNode tree;

    public BSTIteratorOffer2(TreeNode root) {
        queue = new LinkedList<>();
        tree = root;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeNode.deserialize("7,3,15,null,null,9,20");
        BSTIteratorOffer2 solution = new BSTIteratorOffer2(tree);
        System.out.println(solution.next());
        System.out.println(solution.next());
        System.out.println(solution.hasNext());
        System.out.println(solution.next());
        System.out.println(solution.hasNext());
        System.out.println(solution.next());
        System.out.println(solution.hasNext());
        System.out.println(solution.next());
        System.out.println(solution.hasNext());
    }

    public int next() {
        while (tree != null) {
            queue.offerLast(tree);
            tree = tree.left;
        }
        TreeNode node = queue.pollLast();
        tree = node.right;
        return node.val;
//        while (tree != null) {
//            queue.offerLast(tree);
//            tree = tree.left;
//        }
//        TreeNode node = queue.pollLast();
//        tree = node.right;
//        return node.val;
    }

    public boolean hasNext() {
//        return !queue.isEmpty();
        return tree != null || !queue.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
//leetcode submit region end(Prohibit modification and deletion)
