package leetcode.editor.cn;//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 示例1： 
//
// 
//
// 
//输入：l1 = [7,2,4,3], l2 = [5,6,4]
//输出：[7,8,0,7]
// 
//
// 示例2： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[8,0,7]
// 
//
// 示例3： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 100] 
// 0 <= node.val <= 9 
// 输入数据保证链表代表的数字无前导 0 
// 
//
// 
//
// 进阶：如果输入链表不能翻转该如何解决？ 
// Related Topics 栈 链表 数学 👍 460 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

import java.util.Deque;
import java.util.LinkedList;

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
class addTwoNumbersListNodeSolution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode ans = new ListNode(-1);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            carry += stack1.isEmpty() ? 0 : stack1.pop();
            carry += stack2.isEmpty() ? 0 : stack2.pop();
            ListNode node = new ListNode(carry % 10);
            carry /= 10;
            node.next = ans.next;
            ans.next = node;
        }
        if (carry == 1) {
            ListNode carryNode = new ListNode(1);
            carryNode.next = ans.next;
            ans.next = carryNode;
        }
        return ans.next;
    }

    public static void main(String[] args) {
        addTwoNumbersListNodeSolution solution = new addTwoNumbersListNodeSolution();
        ListNode node1 = ListNode.buildLinkedList(7);
        ListNode node2 = ListNode.buildLinkedList(5);
        System.out.println(solution.addTwoNumbers(node1, node2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
