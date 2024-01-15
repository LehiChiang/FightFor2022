package out.bd;

import datastructure.ListNode;

public class OutBDNode {
    public static ListNode MidOrLowMidNode(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode slow = node;
        ListNode fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
