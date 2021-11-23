package codetop;

import datastructure.ListNode;

/**
 * 链表排序问题
 */
class LinkedListSortSolution {

    public static void main(String[] args) {
        LinkedListSortSolution sortSolution = new LinkedListSortSolution();
        ListNode linklist = ListNode.buildLinkedList(new int[]{4, 2, 1, 3, 0, 5}, false);
        ListNode res = sortSolution.sortList(linklist);
        System.out.println(res);
    }

    /**
     * 归并排序版本
     *
     * @param head
     * @return
     */
    public ListNode sortRecurAndMerge(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head.next; // 错误一：应该是head.next
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode leftPart = sortRecurAndMerge(head);
        ListNode rightPart = sortRecurAndMerge(tmp);
        ListNode newList = new ListNode(-1);
        ListNode pointer = newList;
        while (leftPart != null && rightPart != null) {
            if (leftPart.val < rightPart.val) {
                pointer.next = leftPart;
                leftPart = leftPart.next;
            } else {
                pointer.next = rightPart;
                rightPart = rightPart.next;
            }
            pointer = pointer.next;
        }
        pointer.next = leftPart == null ? rightPart : leftPart;
        return newList.next;
    }

    /**
     * 迭代原地排序排序
     *
     * @param head
     * @return
     */
    public ListNode sortIterative(ListNode head) {
        if (head == null)
            return head;
        int len = 0;
        ListNode count = head;
        while (count != null) {
            len++;
            count = count.next;
        }
        ListNode dummyNode = new ListNode(-1, head);
        for (int subLen = 1; subLen < len; subLen = subLen * 2) {
            ListNode pre = dummyNode, cur = dummyNode.next;
            while (cur != null) {
                ListNode head1 = cur;
                for (int i = 1; i < subLen && cur.next != null; i++) {
                    cur = cur.next;
                }
                ListNode head2 = cur.next;
                cur.next = null;
                cur = head2;
                for (int i = 1; i < subLen && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }
                ListNode next = null;
                if (cur != null) {
                    next = cur.next;
                    cur.next = null;
                }
                ListNode sortedSubList = mergeTwoLists(head1, head2);
                pre.next = sortedSubList;
                while (pre.next != null) {
                    pre = pre.next;
                }
                cur = next; // 错误二：不用将所有节点都连上，将cur指向剩余的借点。
            }
        }
        return dummyNode.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newList = new ListNode(-1);
        ListNode pointer = newList, leftPart = l1, rightPart = l2;
        while (leftPart != null && rightPart != null) {
            if (leftPart.val < rightPart.val) {
                pointer.next = leftPart;
                leftPart = leftPart.next;
            } else {
                pointer.next = rightPart;
                rightPart = rightPart.next;
            }
            pointer = pointer.next;
        }
        pointer.next = leftPart == null ? rightPart : leftPart;
        return newList.next;
    }

    public ListNode sortList(ListNode head) {
        return sortIterative(head);
    }
}
