package leetcode.editor.cn;//翻转一棵二叉树。
//
// 示例： 
//
// 输入： 
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9 
//
// 输出： 
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1 
//
// 备注: 
//这个问题是受到 Max Howell 的 原问题 启发的 ： 
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1084 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

class invertTreeSolution {
    public TreeNode invertTree(TreeNode root) {
        return invertLevel(root);
    }

    private TreeNode invertLevel(TreeNode root) {
        if (root == null)
            return root;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null)
                queue.offerLast(node.left);
            if (node.right != null)
                queue.offerLast(node.right);
        }
        return root;
    }

    private TreeNode invert(TreeNode root) {
        if (root == null)
            return root;
        TreeNode left = invert(root.left);
        TreeNode right = invert(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {
        invertTreeSolution solution = new invertTreeSolution();
        TreeNode tree = TreeNode.deserialize("4,2,7,1,3,6,9");
        System.out.println(solution.invertTree(tree));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
