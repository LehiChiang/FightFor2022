package leetcode.editor.cn;//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 10⁵] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 👍 1185 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

import java.util.Deque;
import java.util.LinkedList;

class isPalindromeSolution {
    public boolean isPalindrome(ListNode head) {
        return isPalindromeByReverseList(head);
    }

    private boolean isPalindromeByStack(ListNode head) {
        if (head == null) return false;
        Deque<Integer> stack = new LinkedList<>();
        ListNode slow = head, fast = head.next;
        stack.push(slow.val);
        while (fast != null && fast.next != null) {
            slow = slow.next;
            stack.push(slow.val);
            fast = fast.next.next;
        }
        if (fast == null)
            stack.pop();
        slow = slow.next;
        while (slow != null) {
            int val = stack.pop();
            if (val != slow.val)
                return false;
            slow = slow.next;
        }
        return true;
    }

    private boolean isPalindromeByReverseList(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode firstPart = head;
        ListNode secondPart = reverseList(slow.next);
        while (secondPart != null) {
            if (secondPart.val != firstPart.val) {
                slow.next = reverseList(secondPart);
                return false;
            }
            firstPart = firstPart.next;
            secondPart = secondPart.next;
        }
        slow.next = reverseList(secondPart);
        return true;
    }

    private ListNode reverseList(ListNode list) {
        ListNode temp = new ListNode(-1);
        ListNode p = list;
        while (p != null) {
            ListNode node = p;
            p = p.next;
            node.next = temp.next;
            temp.next = node;
        }
        return temp.next;
    }

    public static void main(String[] args) {
        isPalindromeSolution solution = new isPalindromeSolution();
        System.out.println(solution.isPalindrome(ListNode.buildLinkedList(new int[]{1,2,3,2,4})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
