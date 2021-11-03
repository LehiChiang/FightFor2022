package codetop;

import datastructure.ListNode;

class addTwoNumbersSolution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode resp = res;
        int jinzhi = 0;
        while (l1 != null || l2 != null) {
            jinzhi += l1 != null ? l1.val : 0;
            jinzhi += l2 != null ? l2.val : 0;
            ListNode node = new ListNode(jinzhi % 10);
            resp.next = node;
            resp = resp.next;
            jinzhi /= 10;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if (jinzhi == 1)
            resp.next = new ListNode(1);
        return res.next;
    }

    public static void main(String[] args) {
        addTwoNumbersSolution solution = new addTwoNumbersSolution();
        ListNode listNode1 = ListNode.buildLinkedList(new int[]{9,9}, false);
        ListNode listNode2 = ListNode.buildLinkedList(new int[]{9}, false);
        System.out.println(solution.addTwoNumbers(listNode1, listNode2));
    }
}
