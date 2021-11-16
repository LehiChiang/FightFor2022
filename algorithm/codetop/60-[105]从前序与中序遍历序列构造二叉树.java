package codetop;

import datastructure.TreeNode;

import java.util.HashMap;

class buildTreeSolution {

    private HashMap<Integer, Integer> hashMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder,
                              int[] inorder) {
        for (int i = 0; i < inorder.length; i++)
            hashMap.put(inorder[i], i);
        return buildFromPreInOrder(
                preorder,
                0,
                preorder.length - 1,
                inorder,
                0,
                inorder.length - 1
        );
    }

    private TreeNode buildFromPreInOrder(int[] preOrder,
                                         int preStart,
                                         int preEnd,
                                         int[] inOrder,
                                         int inStart,
                                         int inEnd) {
        if (preStart > preEnd)
            return null;
        TreeNode node = new TreeNode(preOrder[preStart]);
        int rootValIndex = hashMap.get(preOrder[preStart]);
        node.left = buildFromPreInOrder(
                preOrder,
                preStart + 1,
                preStart + rootValIndex - inStart,
                inOrder,
                inStart,
                rootValIndex - 1
        );
        node.right = buildFromPreInOrder(
                preOrder,
                preStart + rootValIndex - inStart + 1,
                preEnd,
                inOrder,
                rootValIndex + 1,
                inEnd
        );
        return node;
    }

    public static void main(String[] args) {
        buildTreeSolution solution = new buildTreeSolution();
        TreeNode tree = solution.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        tree.postTraverse();
    }
}
