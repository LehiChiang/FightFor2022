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
class isPalindromeLinkedOffer2Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reversedRightPart = reverse(slow.next);
        ListNode p1 = head, p2 = reversedRightPart;
        while (p2 != null) {
            if (p1.val != p2.val) {
                slow.next = reverse(reversedRightPart);
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        slow.next = reverse(reversedRightPart);
        return true;
    }

    private ListNode reverse(ListNode rightPart) {
        if (rightPart == null || rightPart.next == null)
            return rightPart;
        ListNode node = reverse(rightPart.next);
        rightPart.next.next = rightPart;
        rightPart.next = null;
        return node;
    }

    public static void main(String[] args) {
        isPalindromeLinkedOffer2Solution solution = new isPalindromeLinkedOffer2Solution();
        System.out.println((solution.isPalindrome(ListNode.buildLinkedList(1))));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
