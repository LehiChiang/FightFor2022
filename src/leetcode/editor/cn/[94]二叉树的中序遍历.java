package leetcode.editor.cn;
//给定一个二叉树的根节点 root ，返回它的 中序 遍历。
// 示例 1：
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 示例 2：
//输入：root = []
//输出：[]
// 示例 3：
//输入：root = [1]
//输出：[1]
// 示例 4：
//输入：root = [1,2]
//输出：[2,1]
// 示例 5：
//输入：root = [1,null,2]
//输出：[1,2]
// 提示：
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 1052 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import leetcode.editor.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class inorderTraversalSolution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
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
}
//leetcode submit region end(Prohibit modification and deletion)
