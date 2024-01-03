package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.ArrayList;
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
class binaryTreePathsSolution {

    private List<String> res;
    public List<String> binaryTreePaths(TreeNode root) {
        res = new ArrayList<>();
        bfs(root, "");
        return res;
    }

    private void bfs(TreeNode root, String path) {
        if (root == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(path);
        sb.append(root.val);
        if (root.left == null && root.right == null)
            res.add(sb.toString());
        else {
            sb.append("->");
            bfs(root.left, sb.toString());
            bfs(root.right, sb.toString());
        }
    }

    public static void main(String[] args) {
        binaryTreePathsSolution solution = new binaryTreePathsSolution();
        System.out.println(solution.binaryTreePaths(TreeNode.deserialize("1,2,3,null,5")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
