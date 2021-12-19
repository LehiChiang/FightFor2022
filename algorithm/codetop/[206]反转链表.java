package codetop;

//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

import static datastructure.ListNode.buildLinkedList;
import static datastructure.ListNode.printLinkedList;

class reverseListSolution {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public static void main(String[] args) {
        reverseListSolution solution = new reverseListSolution();
        ListNode res = solution.reverseList(buildLinkedList());
        printLinkedList(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
