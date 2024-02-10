package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;


class CBTInserterOffer2 {

    private Deque<TreeNode> queue;
    private TreeNode root;

    public CBTInserterOffer2(TreeNode root) {
        this.root = root;
        queue = new LinkedList<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            if (queue.peekFirst().left != null && queue.peekFirst().right != null) {
                TreeNode node = queue.pollFirst();
                queue.offerLast(node.left);
                queue.offerLast(node.right);
            } else if (queue.peekFirst().left != null) {
                queue.offerLast(queue.peekFirst().left);
                break;
            } else break;
        }
    }

    public static void main(String[] args) {
        CBTInserterOffer2 solution = new CBTInserterOffer2(TreeNode.deserialize("1,2,3,4,5,6,null"));
        System.out.println(solution.insert(7));
        System.out.println(solution.insert(8));
        System.out.println(solution.get_root());
    }

    public int insert(int v) {
        TreeNode newNode = new TreeNode(v);
        if (queue.peekFirst().left == null) {
            queue.peekFirst().left = newNode;
            queue.offerLast(newNode);
            return queue.peekFirst().val;
        } else {
            queue.peekFirst().right = newNode;
            queue.offerLast(newNode);
            return queue.pollFirst().val;
        }
    }

    public TreeNode get_root() {
        return this.root;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
//leetcode submit region end(Prohibit modification and deletion)
