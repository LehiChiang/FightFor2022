package codetop;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class levelOrderSolution {

    /**
     * 层序遍历（非递归）
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            ArrayList<Integer> tempList = new ArrayList<>();
            for (int i = queue.size(); i > 0; --i) {
                TreeNode node = queue.poll();
                tempList.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(tempList);
        }
        return res;
    }

    /**
     * 层序遍历（递归）
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderRecur(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        levelOrderInnerRecur(root, 1, res);
        return res;
    }

    private void levelOrderInnerRecur(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return;
        if (res.size() < level)
            res.add(new ArrayList<>());
        res.get(level - 1).add(root.val);
        levelOrderInnerRecur(root.left, level + 1, res);
        levelOrderInnerRecur(root.right, level + 1, res);
    }

    /**
     * 前序遍历（非递归）
     * @param root
     * @return
     */
    public List<Integer> preOrderNotRecur(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    /**
     * 中序遍历（非递归）
     * @param root
     * @return
     */
    public List<Integer> inOrderNotRecur(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }
        return res;
    }

    /**
     * 后序遍历（非递归）
     * @param root
     * @return
     */
    public List<Integer> postOrderNotRecur(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root, prev = null;
        while(!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (node.right == null || node.right == prev) {
                res.add(node.val);
                prev = node;
                node = null;
            } else {
                stack.push(node);
                node = node.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        levelOrderSolution solution = new levelOrderSolution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode rightTree = new TreeNode(20);
        rightTree.left = new TreeNode(15);
        rightTree.right = new TreeNode(7);
        root.right = rightTree;
        System.out.println(solution.levelOrderRecur(root));
    }
}
