package leetcode.editor.cn;//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
//
// 
//
// 为了让您更好地理解问题，以下面的二叉搜索树为例：
// 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是
//第一个节点。 
//
// 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
// 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。 

// 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-
//to-sorted-doubly-linked-list/ 
//
// 注意：此题对比原题有改动。 
// Related Topics 栈 树 深度优先搜索 二叉搜索树 链表 二叉树 双向链表 👍 371 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

class TreeListNode {
    public int val;
    public TreeListNode left;
    public TreeListNode right;

    public TreeListNode() {}

    public TreeListNode(int _val) {
        val = _val;
    }

    public TreeListNode(int _val, TreeListNode _left, TreeListNode _right) {
        val = _val;
        left = _left;
        right = _right;
    }

    @Override
    public String toString() {
        return val + ", " + right;
    }
}

class treeToDoublyListSolution {
    TreeListNode head, tail;
    public TreeListNode treeToDoublyList(TreeListNode root) {
        if (root == null)
            return null;
        dfs(root);
        head.left = tail;
        tail.right = head;
        return head;
    }

    private void dfs(TreeListNode root) {
        if (root == null) return;
        dfs(root.left);
        if (tail != null)
            tail.right = root;
        else
            head = root;
        root.left = tail;
        tail = root;
        dfs(root.right);
    }

    private TreeListNode recur(TreeListNode root) {
        if (root == null)
            return null;
        if (root.left == null || root.right == null)
            return root;
        TreeListNode treeListNode = root;
        TreeListNode rightPart = recur(root.right);
        treeListNode.right = rightPart;
        rightPart.left = treeListNode;
        TreeListNode leftPart = recur(root.left);
        if (leftPart == null)
            return treeListNode;
        else {
            TreeListNode leftPartTail = leftPart;
            while (leftPartTail.right != null) {
                leftPartTail = leftPartTail.right;
            }
            leftPartTail.right = treeListNode;
            treeListNode.left = leftPartTail;
            return leftPart;
        }
    }

    public static void main(String[] args) {
        treeToDoublyListSolution solution = new treeToDoublyListSolution();
        TreeListNode treeListNode = new TreeListNode(4);
        treeListNode.right = new TreeListNode(5);
        TreeListNode leftTreeListNode = new TreeListNode(2);
        leftTreeListNode.left = new TreeListNode(1);
        leftTreeListNode.right = new TreeListNode(3);
        treeListNode.left = leftTreeListNode;
        TreeListNode res = solution.treeToDoublyList(treeListNode);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
