package codetop;

import datastructure.TreeNode;

import java.util.HashMap;

class buildTreeSolution {

    private HashMap<Integer, Integer> hashMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder,
                              int[] inorder) {
        for (int i = 0; i < inorder.length; i++)
            hashMap.put(inorder[i], i);
        return buildFromPreInOrder(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildFromPreInOrder(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd) return null;
        return new TreeNode(preOrder[preStart],
                buildFromPreInOrder(preOrder, preStart + 1, preStart + hashMap.get(preOrder[preStart]) - inStart, inOrder, inStart, hashMap.get(preOrder[preStart]) - 1),
                buildFromPreInOrder(preOrder, preStart + hashMap.get(preOrder[preStart]) - inStart + 1, preEnd, inOrder, hashMap.get(preOrder[preStart]) + 1, inEnd)
        );
    }

    public static void main(String[] args) {
        buildTreeSolution solution = new buildTreeSolution();
        TreeNode tree = solution.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        tree.postTraverse();
    }
}
