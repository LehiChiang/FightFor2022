package leetcode.editor.cn;


//请实现两个函数，分别用来序列化和反序列化二叉树。
//
// 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字
//符串反序列化为原始的树结构。 
//
// 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方
//法解决这个问题。 
//
// 
//
// 示例： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-
//binary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 254 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class serializeCodec {

    public static void main(String[] args) {
        serializeCodec serializeCodec = new serializeCodec();
        System.out.println(serializeCodec.deserialize("1,2,null,null,3,4,null,null,5,null,null,"));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    private String rserialize(TreeNode root, String str) {
        if (root == null)
            str += "null,";
        else {
            str += root.val + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodeArray = data.split(",");
        List<String> nodeList = new ArrayList<>(Arrays.asList(nodeArray));
        return rdeserialize(nodeList);
    }

    private TreeNode rdeserialize(List<String> nodeList) {
        if (nodeList.get(0).equals("null")) {
            nodeList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodeList.get(0)));
        nodeList.remove(0);
        root.left = rdeserialize(nodeList);
        root.right = rdeserialize(nodeList);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
