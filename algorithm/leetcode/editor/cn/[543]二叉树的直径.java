package leetcode.editor.cn;
//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
//
// 
//
// 示例 : 
//给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树 深度优先搜索 二叉树 👍 843 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

class diameterOfBinaryTreeSolution {

    private int diameter = 0;
    // 后序遍历实现
    public int diameterOfBinaryTree(TreeNode root) {
        getDiameter(root);
        return diameter;
    }

    private int getDiameter(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = getDiameter(root.left) + 1;
        int rightHeight = getDiameter(root.right) + 1;
        if (leftHeight + rightHeight - 2 > diameter)
            diameter = leftHeight + rightHeight - 2;
        return Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        diameterOfBinaryTreeSolution solution = new diameterOfBinaryTreeSolution();
        System.out.println(solution.diameterOfBinaryTree(TreeNode.deserialize("1,2,null,4,5,null,null,6,7,null,null,null,8")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
