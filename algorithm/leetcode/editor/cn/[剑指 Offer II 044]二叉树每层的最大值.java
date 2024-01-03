package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class largestValuesOffer2Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int max = queue.peek().val;
            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            list.add(max);
        }
        return list;
    }

    public static void main(String[] args) {
        largestValuesOffer2Solution solution = new largestValuesOffer2Solution();
        System.out.println(solution.largestValues(TreeNode.deserialize("1,null,2")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
