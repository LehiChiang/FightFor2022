package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class sortListOffer2Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode end = slow.next;
        slow.next = null;
        ListNode sortedFront = sortList(head);
        ListNode sortedEnd = sortList(end);
        ListNode dummyNode = new ListNode(-1);
        ListNode pointer = dummyNode;
        while (sortedFront != null && sortedEnd != null) {
            if (sortedEnd.val < sortedFront.val) {
                pointer.next = sortedEnd;
                sortedEnd = sortedEnd.next;
            } else {
                pointer.next = sortedFront;
                sortedFront = sortedFront.next;
            }
            pointer = pointer.next;
        }
        pointer.next = sortedFront != null ? sortedFront : sortedEnd;
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.buildLinkedList(4, 2, 1, 3, 8, 7, 6);
        sortListOffer2Solution solution = new sortListOffer2Solution();
        System.out.println(solution.sortList(node));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
