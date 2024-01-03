package leetcode.editor.cn;//给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
//
// 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//输出：[7,4,1]
//解释：
//所求结点为与目标结点（值为 5）距离为 2 的结点，
//值分别为 7，4，以及 1
//
//
//
//注意，输入的 "root" 和 "target" 实际上是树上的结点。
//上面的输入仅仅是对这些对象进行了序列化描述。
// 
//
// 
//
// 提示： 
//
// 
// 给定的树是非空的。 
// 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。 
// 目标结点 target 是树上的结点。 
// 0 <= K <= 1000. 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 468 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class distanceKSolution {

    private Map<Integer, TreeNode> map = new HashMap<>();
    private List<Integer> res = new ArrayList<>();
    private boolean[] visited = new boolean[500];
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findParent(root);
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        int depth = 0;
        while (!queue.isEmpty()) {
            for (int qSize = queue.size(); qSize > 0; qSize--){
                TreeNode node = queue.poll();
                visited[node.val] = true;
                if (depth == k)
                    res.add(node.val);
                if (node.left != null && !visited[node.left.val])
                    queue.offer(node.left);
                if (node.right != null && !visited[node.right.val])
                    queue.offer(node.right);
                if (map.get(node.val) != null && !visited[map.get(node.val).val])
                    queue.offer(map.get(node.val));
            }
            depth++;
        }
        return res;
    }

    private void findDistanceK(TreeNode target, TreeNode from, int k) {
        if (target == null || k < 0)
            return;
        if (k == 0) {
            res.add(target.val);
            return;
        }
        if (target.left != from)
            findDistanceK(target.left, target, k - 1);
        if (target.right != from)
            findDistanceK(target.right, target, k - 1);
        if (map.get(target.val) != from)
            findDistanceK(map.get(target.val), target, k - 1);
    }

    private void findParent(TreeNode root) {
        if (root.left != null) {
            map.put(root.left.val, root);
            findParent(root.left);
        }
        if (root.right != null) {
            map.put(root.right.val, root);
            findParent(root.right);
        }
    }

    public static void main(String[] args) {
        distanceKSolution solution = new distanceKSolution();
        TreeNode tree = TreeNode.deserialize("3,5,1,6,2,0,8,null,null,7,4");
        System.out.println(solution.distanceK(tree, tree.left, 3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
