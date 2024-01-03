package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)


class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};

class insertOffer2Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node node = head;
        Node insertedNode = new Node(insertVal);
        while (true) {
            // 情况一：在顺序位置插入新节点
            if (node.next.val >= insertVal && node.val <= insertVal)
                break;
            // 情况二：在最大值和最小值中间
            if (node.next.val < node.val) {
                // 大于最大值的节点或者小于最小值的节点
                if (node.val <= insertVal || node.next.val >= insertVal)
                    break;
            }
            // 情况三： 只有一个节点的情况下直接break
            if (node.next == head)
                break;
            node = node.next;
        }
        insertedNode.next = node.next;
        node.next = insertedNode;
        return head;
    }

    public static void main(String[] args) {
        insertOffer2Solution solution = new insertOffer2Solution();
        solution.insert(solution.insert(null, 1), 2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
