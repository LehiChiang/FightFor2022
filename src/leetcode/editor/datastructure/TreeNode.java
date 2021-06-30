package leetcode.editor.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 外部调用前序遍历
     * @param root
     */
    public void preTraverse(TreeNode root){
        System.out.print("Preorder Traversal: ");
        List<String> res = new ArrayList<>();
        preTraverseRecur(root, res);
        System.out.println(res);
    }

    private void preTraverseRecur(TreeNode root, List<String> res){
        if (root == null)
            return;
        res.add(String.valueOf(root.val));
        preTraverseRecur(root.left, res);
        preTraverseRecur(root.right, res);
    }

    /**
     * 外部调用中序遍历
     * @param root
     */
    public void inTraverse(TreeNode root){
        System.out.print("Inorder Traversal: ");
        List<String> res = new ArrayList<>();
        inTraverseRecur(root, res);
        System.out.println(res);
    }

    private void inTraverseRecur(TreeNode root, List<String> res){
        if (root == null)
            return;
        inTraverseRecur(root.left, res);
        res.add(String.valueOf(root.val));
        inTraverseRecur(root.right, res);
    }

    /**
     * 外部调用后序遍历
     * @param root
     */
    public void postTraverse(TreeNode root){
        System.out.print("Postorder Traversal: ");
        List<String> res = new ArrayList<>();
        postTraverseRecur(root, res);
        System.out.println(res);
    }

    private void postTraverseRecur(TreeNode root, List<String> res){
        if (root == null)
            return;
        postTraverseRecur(root.left, res);
        postTraverseRecur(root.right, res);
        res.add(String.valueOf(root.val));
    }

    /**
     * 外部调用层序遍历
     * @param root
     */
    public void levelTraverse(TreeNode root){
        System.out.print("Levelorder Traversal: ");
        List<String> res = levelTraverseBFS(root);
        System.out.println(res);
    }

    private List<String> levelTraverseBFS(TreeNode root){
        if (root == null)
            return new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<String> res = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(String.valueOf(node.val));
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return res;
    }
}
