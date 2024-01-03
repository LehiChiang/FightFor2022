package leetcode.editor.cn;//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
// Related Topics 链表 双指针 👍 1628 👎 0


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
class removeNthFromEndSolution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head, cur = head;
        while (cur.next != null && n > 0) {
            cur = cur.next;
            n--;
        }
        if (cur.next == null && n != 0)
            return head.next;
        while (cur.next != null) {
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    public static void main(String[] args) {
        removeNthFromEndSolution solution = new removeNthFromEndSolution();
        System.out.println(solution.removeNthFromEnd(
                ListNode.buildLinkedList(new int[]{1,2,3,4,5,6,7,8,9}, false),
                9
        ));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
