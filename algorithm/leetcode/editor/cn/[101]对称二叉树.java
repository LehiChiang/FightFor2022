package leetcode.editor.cn;
//给定一个二叉树，检查它是否是镜像对称的。
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1630 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

class isSymmetricSolution {

    public boolean isSymmetric(TreeNode root) {
        return iterate(root, root);
    }

    private boolean iterate(TreeNode leftBranch, TreeNode rightBranch) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(leftBranch);
        queue.add(rightBranch);
        while (!queue.isEmpty()) {
            TreeNode tree1 = queue.poll();
            TreeNode tree2 = queue.poll();

            if (tree1 == null && tree2 == null)
                continue;
            if ((tree1 == null || tree2 == null) || tree1.val != tree2.val)
                return false;

            queue.offer(tree1.left);
            queue.offer(tree2.right);
            queue.offer(tree1.right);
            queue.offer(tree2.left);
        }
        return true;
    }

    private boolean dfs(TreeNode leftBranch, TreeNode rightBranch) {
        if (leftBranch == null && rightBranch == null)
            return true;
        if (leftBranch == null || rightBranch == null || leftBranch.val != rightBranch.val)
            return false;
        return dfs(leftBranch.left, rightBranch.right) && dfs(leftBranch.right, rightBranch.left);
    }

    public static void main(String[] args) {
        isSymmetricSolution solution = new isSymmetricSolution();
        System.out.println(solution.isSymmetric(TreeNode.deserialize("1,2,2,3,4,4,3")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
