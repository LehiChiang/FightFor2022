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
class removeNthFromEndOffer2Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) return head;
        ListNode node = head;
        while (node != null) {
            n--;
            node = node.next;
        }
        if (n > 0) return head;
        else if (n == 0) return head.next;
        else {
            ListNode pre = head;
            while (++n != 0)
                pre = pre.next;
            pre.next = pre.next.next;
            return head;
        }
    }

    public static void main(String[] args) {
        removeNthFromEndOffer2Solution solution = new removeNthFromEndOffer2Solution();
        System.out.println(solution.removeNthFromEnd(ListNode.buildLinkedList(1,2,3,4,5), 2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
