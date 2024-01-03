package leetcode.editor.cn;
//给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
// 每条从根节点到叶节点的路径都代表一个数字：
// 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
// 计算从根节点到叶节点生成的 所有数字之和 。
// 叶节点 是指没有子节点的节点。
// 示例 1：
//输入：root = [1,2,3]
//输出：25
//解释：
//从根到叶子节点路径 1->2 代表数字 12
//从根到叶子节点路径 1->3 代表数字 13
//因此，数字总和 = 12 + 13 = 25
// 示例 2：
//输入：root = [4,9,0,5,1]
//输出：1026
//解释：
//从根到叶子节点路径 4->9->5 代表数字 495
//从根到叶子节点路径 4->9->1 代表数字 491
//从根到叶子节点路径 4->0 代表数字 40
//因此，数字总和 = 495 + 491 + 40 = 1026
// 提示：
// 树中节点的数目在范围 [1, 1000] 内 
// 0 <= Node.val <= 9 
// 树的深度不超过 10
// Related Topics 树 深度优先搜索 二叉树 👍 438 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

class sumNumbersSolution {

    /**
     * 递归写法
     * @param root
     * @return
     */
    public int sumNumbersRecur(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int preSum) {
        if (root == null)
            return 0;
        int sum = preSum * 10 + root.val;
        if (root.left == null && root.right == null)
            return sum;
        else
            return dfs(root.left, sum) + dfs(root.right, sum);
    }

    /**
     * 层续遍历写法
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valQueue = new LinkedList<>();
        nodeQueue.offer(root);
        valQueue.offer(root.val);
        int res = 0;
        while (!nodeQueue.isEmpty() && !valQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int val = valQueue.poll();
            if (node.left == null && node.right == null) {
                res += val;
            }
            if (node.left != null) {
                nodeQueue.offer(node.left);
                valQueue.offer(val * 10 + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                valQueue.offer(val * 10 + node.right.val);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(
                4,
                new TreeNode(
                        9,
                        new TreeNode(5),
                        new TreeNode(1)
                ),
                new TreeNode(
                        0
                )
        );
//        TreeNode tree = new TreeNode(
//                1,
//                new TreeNode(
//                        2
//                ),
//                new TreeNode(
//                        3
//                )
//        );
        sumNumbersSolution solution = new sumNumbersSolution();
        int res = solution.sumNumbers(tree);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
