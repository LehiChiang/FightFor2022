package leetcode.editor.cn;
//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
// 示例 1：
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
//
// 示例 2：
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
//
// 提示：
// 链表中节点的数目在范围 [0, 500] 内 
// -100 <= Node.val <= 100 
// 0 <= k <= 2 * 10⁹
// Related Topics 链表 双指针 👍 631 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import leetcode.editor.datastructure.ListNode;

class rotateRightSolution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;
        int nodeNum = 0;
        ListNode cntPoint = head;
        while (cntPoint != null) {
            nodeNum++;
            cntPoint = cntPoint.next;
        }
        int step = k % nodeNum;
        if (step == 0)
            return head;
        ListNode newHead = head, pointer = newHead, headTail = null;
        while (step > 1) {
            pointer = pointer.next;
            step--;
        }
        while (pointer.next != null) {
            headTail = newHead;
            newHead = newHead.next;
            pointer = pointer.next;
        }
        headTail.next = null;
        pointer.next = head;
        return newHead;
    }

    public static void main(String[] args) {
        rotateRightSolution solution = new rotateRightSolution();
        ListNode listNode = solution.rotateRight(ListNode.buildLinkedList(new int[]{1}), 1);
        System.out.println(listNode);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
