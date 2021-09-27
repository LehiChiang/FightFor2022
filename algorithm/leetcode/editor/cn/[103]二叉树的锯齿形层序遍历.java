package leetcode.editor.cn;
//给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，
// 以此类推，层与层之间交替进行）。
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7],
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 返回锯齿形层序遍历如下：
//[
//  [3],
//  [20,9],
//  [15,7]
//]
// Related Topics 树 广度优先搜索 二叉树 
// 👍 487 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.*;

class zigzagLevelOrderSolution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            LinkedList<Integer> row = new LinkedList<>();
            TreeNode node;
            for (int num = queue.size(); num > 0; num--) {
                node = queue.pop();
                if (reverse)
                    row.offerFirst(node.val);
                else row.offerLast(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            res.add(row);
            reverse = !reverse;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
