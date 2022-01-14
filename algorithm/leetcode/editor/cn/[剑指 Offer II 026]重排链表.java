package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class reorderListOffer2Solution {
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head;
        int leftCount = 0;
        // 链表分段
        while (fast != null && fast.next !=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 反转链表
        ListNode cur = slow.next, pre = null;
        slow.next = null;
        while (cur != null) {
            leftCount++;
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 见缝插针
        cur = head;
        for (int i = 0; i < leftCount; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = pre.next;
            cur.next.next = next;
            cur = next;
        }
    }

    private ListNode reverse(ListNode head) {
        if (head.next == null)
            return head;
        ListNode node = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;

    }

    public static void main(String[] args) {
        reorderListOffer2Solution solution = new reorderListOffer2Solution();
        solution.reorderList(ListNode.buildLinkedList(1, 2, 3, 4, 5, 6));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
