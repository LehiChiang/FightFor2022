package codetop;

import datastructure.ListNode;

class reverseBetweenSolution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        reverseBetweenSolution solution = new reverseBetweenSolution();
        System.out.println(solution.reverseBetween(ListNode.buildLinkedList(new int[]{1, 2, 3, 4, 5}, false), 2, 4));
    }
}
