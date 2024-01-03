package leetcode.editor.cn;
//给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。
// 示例 1:
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 示例 2:
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 提示:
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均无重复元素 
// inorder 均出现在 preorder 
// preorder 保证为二叉树的前序遍历序列 
// inorder 保证为二叉树的中序遍历序列
// Related Topics 树 数组 哈希表 分治 二叉树 
// 👍 1148 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.HashMap;

class buildTreeFrompreandmidSolution {

    HashMap<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTreeFromPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    /**
     * 元素控制只包含要使用的，使用过的不包括哦
     * @param preorder
     * @param preStart
     * @param preEnd
     * @param inorder
     * @param inStart
     * @param inEnd
     * @return
     */
    private TreeNode buildTreeFromPreIn(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int inorderRoot = indexMap.get(preorder[preStart]);
        TreeNode root = new TreeNode(preorder[preStart]);
        root.left = buildTreeFromPreIn(preorder, preStart + 1, preStart + inorderRoot - inStart, inorder, inStart, inorderRoot - 1);
        root.right = buildTreeFromPreIn(preorder, preStart + inorderRoot - inStart + 1, preEnd, inorder, inorderRoot + 1, inEnd);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
