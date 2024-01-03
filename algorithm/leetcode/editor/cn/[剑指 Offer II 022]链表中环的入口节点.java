package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class detectCycleOffer2Solution {
    public ListNode detectCycle(ListNode head) {
        boolean hasCycle = false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        detectCycleOffer2Solution solution = new detectCycleOffer2Solution();
        System.out.println(solution.detectCycle(ListNode.buildLinkedList(3,2,0,-4)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
