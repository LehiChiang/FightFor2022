package leetcode.editor.cn;

//给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
// 示例 1：
//输入：n = 3
//输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
// 示例 2：
//输入：n = 1
//输出：[[1]]
// 提示：
// 1 <= n <= 8
// Related Topics 树 二叉搜索树 动态规划 回溯 二叉树 👍 1004 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

class generateTreesSolution {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList<>();
        return count(1, n);
    }

    private List<TreeNode> count(int low, int high) {
        List<TreeNode> res = new ArrayList<>();
        if (low > high) {
            res.add(null);
            return res;
        }
        for (int i = low; i <= high; i++) {
            List<TreeNode> leftTrees = count(low, i - 1);
            List<TreeNode> rightTrees = count(i + 1, high);
            for (TreeNode left : leftTrees)
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
        }
        return res;
    }

    public static void main(String[] args) {
        generateTreesSolution solution = new generateTreesSolution();
        System.out.println(solution.generateTrees(3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
