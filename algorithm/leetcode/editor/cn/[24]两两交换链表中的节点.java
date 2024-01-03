package leetcode.editor.cn;//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
// Related Topics 递归 链表 👍 1191 👎 0


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
class swapPairsSolution {
    public ListNode swapPairs(ListNode head) {
        ListNode newNode = new ListNode(-1, head);
        ListNode tmp = newNode;
        while (tmp.next != null && tmp.next.next != null) {
            ListNode node1 = tmp.next;
            ListNode node2 = tmp.next.next;
            tmp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            tmp = node1;
        }
        return newNode.next;
    }

    private ListNode splitNode(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode listA = head, listB = head.next;
        ListNode pa = listA, pb = listB;
        while (pb != null && pb.next != null) {
            pa.next = pa.next.next;
            pa = pa.next;
            pb.next = pb.next.next;
            pb = pb.next;
        }
        pa.next = null;
        ListNode newNode = new ListNode(-1);
        ListNode p = newNode;
        while (listA != null || listB != null) {
            if (listB != null) {
                p.next = listB;
                listB = listB.next;
                p = p.next;
            }
            if (listA != null) {
                p.next = listA;
                listA = listA.next;
                p = p.next;
            }
        }
        return newNode.next;
    }

    public static void main(String[] args) {
        swapPairsSolution solution = new swapPairsSolution();
        System.out.println(solution.swapPairs(ListNode.buildLinkedList(1,2,3,4)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
