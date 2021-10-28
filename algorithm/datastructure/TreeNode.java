package datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {

    /**
     * 树节点值
     */
    public int val;
    /**
     * 左子树
     */
    public TreeNode left;
    /**
     * 右子树
     */
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
     * 字符串构造二叉树
     * @param data
     * @param SEP
     * @param NULL
     * @return
     */
    public static TreeNode deserialize(String data, String SEP, String NULL) {
        if (data.isEmpty()) return null;
        String[] values = data.split(SEP);
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1 ; i < values.length ; ){
            TreeNode cur = queue.poll();
            String ch = values[i++];
            if (!ch.equals(NULL)){
                TreeNode left = new TreeNode(Integer.parseInt(ch));
                cur.left = left;
                queue.offer(cur.left);
            }else {
                cur.left = null;
            }

            String ch2 = values[i++];
            if (!ch2.equals(NULL)){
                TreeNode right = new TreeNode(Integer.parseInt(ch2));
                cur.right = right;
                queue.offer(cur.right);
            }else {
                cur.right = null;
            }
        }
        return root;
    }

    /**
     * 外部调用前序遍历
     * @param root 要遍历的树
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
     * @param root 要遍历的树
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
     * @param root 要遍历的树
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
     * @param root 要遍历的树
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

    @Override
    public String toString() {
        return levelTraverseBFS(this).toString();
    }
}
