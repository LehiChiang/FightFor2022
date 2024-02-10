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
class reverseListOffer2Solution {
    public static void main(String[] args) {
        reverseListOffer2Solution solution = new reverseListOffer2Solution();
        System.out.println(solution.reverseList(ListNode.buildLinkedList(1, 2, 3, 4, 5)));
    }

    public ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        return recurReverse(head);
    }

    private ListNode recurReverse(ListNode head) {
        if (head.next == null)
            return head;
        ListNode node = recurReverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    private ListNode insertReverse(ListNode head) {
        ListNode newNode = new ListNode(-1);
        while (head != null) {
            ListNode node = head;
            head = head.next;
            node.next = newNode.next;
            newNode.next = node;
        }
        return newNode.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
