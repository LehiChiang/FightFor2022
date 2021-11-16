package codetop;

import datastructure.TreeNode;

import java.util.HashMap;
import java.util.Stack;

class outputTreeSolution {

    private HashMap<Integer, Integer> hashMap = new HashMap<>();
    private Stack<TreeNode> stack = new Stack<>();

    public Stack<TreeNode> printTree(int[] preorder,
                                     int[] inorder) {
        for (int i = 0; i < inorder.length; i++)
            hashMap.put(inorder[i], i);
        outputFromPreInOrder(
                preorder,
                0,
                preorder.length - 1,
                inorder,
                0,
                inorder.length - 1
        );
        return stack;
    }

    private TreeNode outputFromPreInOrder(int[] preOrder,
                                         int preStart,
                                         int preEnd,
                                         int[] inOrder,
                                         int inStart,
                                         int inEnd) {
        if (preStart > preEnd)
            return null;
        TreeNode node = new TreeNode(preOrder[preStart]);
        if (preStart != preEnd)
            stack.push(node);
        int rootValIndex = hashMap.get(preOrder[preStart]);
        TreeNode right = outputFromPreInOrder(preOrder, preStart + rootValIndex - inStart + 1, preEnd, inOrder, rootValIndex + 1, inEnd);
        if (right != null) stack.push(right);
        TreeNode left = outputFromPreInOrder(preOrder, preStart + 1, preStart + rootValIndex - inStart, inOrder, inStart, rootValIndex - 1);
        if (left != null) stack.push(left);
        return node;
    }

    public static void main(String[] args) {
        outputTreeSolution solution = new outputTreeSolution();
        Stack<TreeNode> res = solution.printTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        // 后续遍历 9，15，7，20，3
        while (!res.isEmpty()){
            System.out.print(" " + res.pop().val);
        }
    }
}
