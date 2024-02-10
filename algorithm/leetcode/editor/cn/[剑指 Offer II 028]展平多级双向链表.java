package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

class ChildNode {
    public int val;
    public ChildNode prev;
    public ChildNode next;
    public ChildNode child;
};

class flattenOffer2Solution {
    public ChildNode flatten(ChildNode head) {
        flattenList(head);
        return head;
    }

    private ChildNode flattenList(ChildNode head) {
        ChildNode last = head;
        while (head != null) {
            if (head.child == null) {
                last = head;
                head = head.next;
            } else {
                ChildNode tmp = head.next;
                ChildNode childLast = flattenList(head.child);
                head.next = head.child;
                head.child.prev = head;
                head.child = null;
                if (childLast != null) childLast.next = tmp;
                if (tmp != null) tmp.prev = childLast;
                last = head;
                head = childLast;
            }
        }
        return last;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
