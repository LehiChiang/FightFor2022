package leetcode.editor.cn;

//给你链表的头结点 head ，请将其按升序排列并返回排序后的链表 。
//
// 进阶：
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
// 示例 1：
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 示例 2：
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 示例 3：
//输入：head = []
//输出：[]
// 提示：
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵
// Related Topics 链表 双指针 分治 排序 归并排序 👍 1356 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

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
class sortListSolution {

    /**
     * 自顶向下的归并排序，时间复杂度O(nlogn),空间复杂度O(logn)（栈所用空间）
     * @param head
     * @return
     */
    public ListNode sortListTopDown(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode leftMerged = sortList(head);
        ListNode rightMerged = sortList(tmp);

        ListNode newMergedLinkedList = new ListNode(-1);
        ListNode pointer = newMergedLinkedList;

        while (leftMerged != null && rightMerged != null) {
            if (leftMerged.val < rightMerged.val) {
                pointer.next = leftMerged;
                leftMerged = leftMerged.next;
            } else {
                pointer.next = rightMerged;
                rightMerged = rightMerged.next;
            }
            pointer = pointer.next;
        }
        pointer.next = leftMerged == null ? rightMerged : leftMerged;
        return newMergedLinkedList.next;
    }

    /**
     * 自底向上的归并排序，时间复杂度O(nlogn),空间复杂度O(1)
     * @param head
     * @return
     */
    public ListNode sortListBottomUp(ListNode head) {
        if (head == null)
            return head;
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyNode = new ListNode(-1, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode pre = dummyNode, cur = dummyNode.next;
            while (cur != null) {
                ListNode head1 = cur;
                for (int i = 1; i < subLength && cur.next != null ; i++) {
                    cur = cur.next;
                }
                ListNode head2 = cur.next;
                cur.next = null;
                cur = head2;
                for (int j = 1; j < subLength && cur != null && cur.next != null; j++) {
                    cur = cur.next;
                }
                ListNode next = null;
                if (cur != null) {
                    next = cur.next;
                    cur.next = null;
                }
                ListNode mergedList = mergeTwoLists(head1, head2);
                pre.next = mergedList;
                while (pre.next != null) {
                    pre = pre.next;
                }
                cur = next;
            }
        }
        return dummyNode.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(-1);
        ListNode pre = newHead, temp1 = l1, temp2 = l2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                pre.next = temp1;
                temp1 = temp1.next;
            } else {
                pre.next = temp2;
                temp2 = temp2.next;
            }
            pre = pre.next;
        }
        pre.next = temp1 == null ? temp2 : temp1;
        return newHead.next;
    }

    public ListNode sortList(ListNode head) {
        return sortListBottomUp(head);
    }

    public static void main(String[] args) {
        sortListSolution sortListSolution = new sortListSolution();
        ListNode linklist = ListNode.buildLinkedList(new int[]{4, 2, 1, 3, 0}, false);
        ListNode res = sortListSolution.sortList(linklist);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
