package leetcode.editor.cn;
//给定一个单链表 L 的头节点 head ，单链表 L 表示为：
// L0 → L1 → … → Ln-1 → Ln 
//请将其重新排列后变为：
// L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
// 示例 1:
//输入: head = [1,2,3,4]
//输出: [1,4,2,3] 
//
// 示例 2:
//输入: head = [1,2,3,4,5]
//输出: [1,5,2,4,3] 
//
// 提示：
// 链表的长度范围为 [1, 5 * 10⁴] 
// 1 <= node.val <= 1000
// Related Topics 栈 递归 链表 双指针 👍 693 👎 0


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
class reorderListSolution {
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head;
        int leftCount = 0;
        // 链表分段
        while (fast != null && fast.next !=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 反转链表
        ListNode cur = slow.next, pre = null;
        slow.next = null;
        while (cur != null) {
            leftCount++;
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 见缝插针
        cur = head;
        for (int i = 0; i < leftCount; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = pre.next;
            cur.next.next = next;
            cur = next;
        }
        System.out.println(head);
    }

    public static void main(String[] args) {
        reorderListSolution solution = new reorderListSolution();
        solution.reorderList(ListNode.buildLinkedList(new int[]{1,2,3,4,5}, false));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
