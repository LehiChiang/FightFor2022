package leetcode.editor.codetop;

import leetcode.editor.datastructure.ListNode;

class getIntersectionNodeSolution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode hA = headA, hB = headB;
        while (hA != hB) {
            if (hA.next == null && hB.next == null)
                return null;
            hA = hA.next != null ? hA.next : headB;
            hB = hB.next != null ? hB.next : headA;
        }
        return hA;
    }
}
