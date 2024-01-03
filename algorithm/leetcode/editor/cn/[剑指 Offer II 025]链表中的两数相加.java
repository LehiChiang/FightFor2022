package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

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
class addTwoNumbersOffer2Solution {
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
        addTwoNumbersOffer2Solution solution = new addTwoNumbersOffer2Solution();
        System.out.println(solution.addTwoNumbers(ListNode.buildLinkedList(7,2,4,3), ListNode.buildLinkedList(5,6,4)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
