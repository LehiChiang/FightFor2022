package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class findTargetSolution {
    public boolean findTarget(TreeNode root, int k) {
        return BFSHashSet(root, k);
    }

    private boolean BFSHashSet(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (set.contains(k - node.val))
                return true;
            set.add(node.val);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
        return false;
    }

    private boolean DFSHashSet(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return find(root, k, set);
    }

    private boolean find(TreeNode root, int k, Set<Integer> set) {
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }


    public static void main(String[] args) {
        findTargetSolution solution = new findTargetSolution();
        System.out.println(solution.findTarget(TreeNode.deserialize("5,3,6"), 9));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
