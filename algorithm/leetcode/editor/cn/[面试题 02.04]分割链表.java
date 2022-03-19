package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class partitionSolution {
    public ListNode partition(ListNode head, int x) {
        ListNode gt = new ListNode(), gtPre = gt, st = new ListNode(), stPre = st;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                stPre.next = cur;
                stPre = stPre.next;
            }
            if (cur.val >= x) {
                gtPre.next = cur;
                gtPre = gtPre.next;
            }
            cur = cur.next;
        }
        gtPre.next = null;
        stPre.next = gt.next;
        return st.next;
    }

    public static void main(String[] args) {
        partitionSolution solution = new partitionSolution();
        System.out.println(solution.partition(ListNode.buildLinkedList(1,4,3,2,5,2), 3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
