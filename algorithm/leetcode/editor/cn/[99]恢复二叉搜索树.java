package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

class recoverTreeSolution {

    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node1 = null, node2 = null;
        TreeNode preNode = new TreeNode(Integer.MIN_VALUE);
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val < preNode.val) {
                node1 = root;
                if (node2 == null) {
                    node2 = preNode;
                } else
                    break;
            }
            preNode = root;
            root = root.right;
        }
        swap(node1, node2);
    }

    private void swap(TreeNode node1, TreeNode node2) {
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }

    public static void main(String[] args) {
        recoverTreeSolution solution = new recoverTreeSolution();
        solution.recoverTree(TreeNode.deserialize("3,1,4,null,null,2,null"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
