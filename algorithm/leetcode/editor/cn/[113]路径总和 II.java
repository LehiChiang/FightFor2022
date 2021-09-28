package leetcode.editor.cn;

//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
// 叶子节点 是指没有子节点的节点。
// 示例 1：
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 示例 2：
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 示例 3：
//输入：root = [1,2], targetSum = 0
//输出：[]
// 提示：
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000
// Related Topics 树 深度优先搜索 回溯 二叉树 👍 583 👎 0


import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class pathSum2Solution {

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return res;
    }

    private void dfs(TreeNode root, int targetSum) {
        if (root == null)
            return;
        path.offerLast(root.val);
        targetSum -= root.val;
        if (targetSum == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(path));
        }
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);
        path.pollLast();
    }

    public static void main(String[] args) {
        pathSum2Solution solution = new pathSum2Solution();
        TreeNode root = new TreeNode(5);
        TreeNode node_4 = new TreeNode(4);
        TreeNode node_8 = new TreeNode(8);
        TreeNode node_11 = new TreeNode(11);
        TreeNode node_13 = new TreeNode(13);
        TreeNode node__4 = new TreeNode(4);
        TreeNode node_7 = new TreeNode(7);
        TreeNode node_2 = new TreeNode(2);
        TreeNode node_1 = new TreeNode(1);
        node_11.left = node_7;
        node_11.right = node_2;
        node__4.right = node_1;
        node_4.left = node_11;
        node_8.left = node_13;
        node_8.right = node__4;
        root.left = node_4;
        root.right = node_8;
        System.out.println(solution.pathSum(root, 22));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
