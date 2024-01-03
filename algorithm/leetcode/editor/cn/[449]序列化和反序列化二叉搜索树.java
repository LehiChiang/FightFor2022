package leetcode.editor.cn;//序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
//
// 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序
//列化为最初的二叉搜索树。 
//
// 编码的字符串应尽可能紧凑。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [2,1,3]
//输出：[2,1,3]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数范围是 [0, 10⁴] 
// 0 <= Node.val <= 10⁴ 
// 题目数据 保证 输入的树是一棵二叉搜索树。 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 二叉搜索树 字符串 二叉树 👍 236 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Codec {

    private String NULL = "null";
    private String SEP = ",";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
         if (root == null)
             return NULL;
        Deque<TreeNode> deque = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        deque.offer(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            if (node == null) {
                sb.append(NULL + SEP);
                continue;
            }
            sb.append(node.val + SEP);
            if (node.left == null && node.right == null)
                continue;
            deque.offer(node.left);
            deque.offer(node.right);
        }
        return sb.substring(0, sb.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] array = data.split(",");
        if (array.length == 0)
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < array.length; ) {
            TreeNode node = queue.poll();
            if (array[i].equals(NULL)) {
                node.left = null;
                i++;
            } else {
                node.left = new TreeNode(Integer.parseInt(array[i++]));
                queue.offer(node.left);
            }

            if (array[i].equals(NULL)) {
                node.right = null;
                i++;
            } else {
                node.right = new TreeNode(Integer.parseInt(array[i++]));
                queue.offer(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        System.out.println(codec.deserialize("2,null,3,null,4,null,5"));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
//leetcode submit region end(Prohibit modification and deletion)
