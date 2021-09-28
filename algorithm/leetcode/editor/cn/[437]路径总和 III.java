package leetcode.editor.cn;

//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
// 示例 1：
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
// 示例 2：
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 提示:
// 二叉树的节点个数的范围是 [0,1000] 
// -10⁹ <= Node.val <= 10⁹ 
// -1000 <= targetSum <= 1000
// Related Topics 树 深度优先搜索 二叉树 👍 1026 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
import datastructure.TreeNode;

import java.util.HashMap;

class pathSum3Solution {

//    private int res = 0;
//    public int pathSum(TreeNode root, int targetSum) {
//        if (root == null)
//            return 0;
//        dfs(root, targetSum);
//        pathSum(root.left, targetSum);
//        pathSum(root.right, targetSum);
//        return res;
//    }
//
//    private void dfs(TreeNode root, int targetSum) {
//        if (root == null)
//            return;
//        if (targetSum == root.val)
//            res++;
//        targetSum -= root.val;
//        dfs(root.left, targetSum);
//        dfs(root.right, targetSum);
//    }

    /**
     * 前缀和思想
     * @param root 树
     * @param targetSum 目标值
     * @return
     */
    HashMap<Integer, Integer> map = new HashMap<>();
    public int pathSum(TreeNode root, int targetSum) {
        map.put(0, 1);
        return recurPreSum(root, targetSum, 0);
    }

    private int recurPreSum(TreeNode root, int targetSum, int currentSum) {
        if (root == null)
            return 0;
        int res = 0 ;
        currentSum += root.val;
        res += map.getOrDefault(currentSum - targetSum, 0);
        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        res += recurPreSum(root.left, targetSum, currentSum);
        res += recurPreSum(root.right, targetSum, currentSum);
        map.put(currentSum, map.get(currentSum) - 1);
        return res;
    }

    public static void main(String[] args) {
        pathSum3Solution solution = new pathSum3Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        System.out.println(solution.pathSum(root, -1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
