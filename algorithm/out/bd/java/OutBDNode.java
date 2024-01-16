package out.bd.java;

import datastructure.ListNode;

public class OutBDNode {
    /**
     * 无头结点链表，奇数节点个数返回中间节点，偶数节点个数返回下中间节点
     *
     * @param head
     * @return
     */
    public static ListNode MidOrLowMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 无头结点链表，奇数节点个数返回中间节点，偶数节点个数返回上中间节点
     *
     * @param head
     * @return
     */
    public static ListNode MidOrUpMidNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 带头结点链表，奇数节点个数返回中间节点，偶数节点个数返回上中间前一个节点
     *
     * @param head
     * @return
     */
    public static ListNode MidOrUpMidPreNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return null;
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return fast == null ? slow.next : slow;
    }

    /**
     * 带头结点链表，奇数节点个数返回中间节点，偶数节点个数返回下中间前一个节点
     *
     * @param head
     * @return
     */
    public static ListNode MidOrDownMidPreNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return null;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return fast == null ? slow : slow.next;
    }
}
