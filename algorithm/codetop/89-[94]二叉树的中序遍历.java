package codetop;

import datastructure.TreeNode;

import java.util.*;

class inOrderSolution {

    /**
     * 非递归中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        while (root != null || !queue.isEmpty()) {
            while (root != null) {
                queue.push(root);
                root = root.left;
            }
            root = queue.poll();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    public static void main(String[] args) {
        inOrderSolution solution = new inOrderSolution();
        TreeNode node = TreeNode.deserialize("1,null,2,3,null", ",", "null");
        System.out.println(solution.inorderTraversal(node));
    }
}
