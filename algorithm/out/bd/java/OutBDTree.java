package out.bd.java;

import datastructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class OutBDTree {
    /**
     * 非递归-先序遍历
     * 0. 先将根节点入栈
     * 1. 弹出栈，打印元素
     * 2. 有右子树，右子树入栈
     * 3. 有左子树，左子树入栈
     *
     * @param root
     */
    public static void PreOrderTraverse(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                System.out.print(node.val + " ");
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }
    }

    /**
     * 非递归-后序遍历
     * 0. 先将根节点入栈
     * 1. 弹出栈，压入辅助栈中
     * 2. 有左子树，左子树入栈
     * 3. 有右子树，右子树入栈
     * 4. 辅助栈元素全部弹出
     *
     * @param root
     */
    public static void PostOrderTraverse(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            stack1.push(root);
            while (!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                stack2.push(node);
                if (node.left != null) stack1.push(node.left);
                if (node.right != null) stack1.push(node.right);
            }
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().val + " ");
            }
        }
    }

    /**
     * 非递归-中序遍历
     * 1. 一直压入左子树，直到左子树为空
     * 2. 出栈，输出元素
     * 3. 当前节点赋值为右子树
     *
     * @param root
     */
    public static void InOrderTraverse(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    System.out.print(root.val + " ");
                    root = root.right;
                }
            }
        }
    }

    /**
     * 广度优先遍历（层序遍历）
     *
     * @param root
     */
    public static void LayerTraverse(TreeNode root) {
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
    }

    public static int MaxNodeNumsInLayer(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode curLayerLastNode = root;
        TreeNode nextLayerLastNode = null;
        int curLayerNodeNums = 0;
        int maxNodeNums = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                nextLayerLastNode = node.left;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLayerLastNode = node.right;
            }
            curLayerNodeNums++; //统计这一层节点数量
            if (node == curLayerLastNode) {
                maxNodeNums = Math.max(maxNodeNums, curLayerNodeNums);
                curLayerNodeNums = 0;
                curLayerLastNode = nextLayerLastNode;
            }
        }
        return maxNodeNums;
    }
}
