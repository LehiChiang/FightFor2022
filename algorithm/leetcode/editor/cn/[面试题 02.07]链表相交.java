package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class getIntersectionNodeSolutionInterview {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode newA = headA, newB = headB;
        while (newA != newB) {
            newA = newA != null ? newA.next : headB;
            newB = newB != null ? newB.next : headA;
        }
        return newA;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
