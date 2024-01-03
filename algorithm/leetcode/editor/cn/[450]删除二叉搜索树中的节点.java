package leetcode.editor.cn;
//给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的
//根节点的引用。
// 一般来说，删除节点可分为两个步骤：
// 首先找到需要删除的节点； 
// 如果找到了，删除它。
// 示例 1:
//输入：root = [5,3,6,2,4,null,7], key = 3
//输出：[5,4,6,2,null,null,7]
//解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
//一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
//另一个正确答案是 [5,2,6,null,4,null,7]。
//
// 示例 2:
//输入: root = [5,3,6,2,4,null,7], key = 0
//输出: [5,3,6,2,4,null,7]
//解释: 二叉树不包含值为 0 的节点
//
// 示例 3:
//输入: root = [], key = 0
//输出: [] 
//
// 提示:
// 节点数的范围 [0, 10⁴]. 
// -10⁵ <= Node.val <= 10⁵ 
// 节点值唯一 
// root 是合法的二叉搜索树 
// -10⁵ <= key <= 10⁵ 
//
// 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。 
// Related Topics 树 二叉搜索树 二叉树 👍 592 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

class deleteNodeSolution {

    /**
     * 删除某节点，可以用左子树的最大值或者右子树的最小值替换
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        return delete(root, key);
    }

    private TreeNode delete(TreeNode root, int key) {
        if (root == null)
            return root;
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                TreeNode min = getMinNode(root.right);
                root.val = min.val;;
                root.right = delete(root.right, min.val);
            }
        } else if (root.val < key) {
            root.right = delete(root.right, key);
        } else {
            root.left = delete(root.left, key);
        }
        return root;
    }

    // 这里选右子树最小值替换要删除的节点
    private TreeNode getMinNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        deleteNodeSolution solution = new deleteNodeSolution();
        TreeNode root = TreeNode.deserialize("5,3,6,2,4,null,7");
        System.out.println(solution.deleteNode(root,  4));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
