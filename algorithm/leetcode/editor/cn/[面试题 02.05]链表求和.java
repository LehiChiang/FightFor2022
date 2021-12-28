package leetcode.editor.cn;//给定两个用链表表示的整数，每个节点包含一个数位。
//
// 这些数位是反向存放的，也就是个位排在链表首部。 
//
// 编写函数对这两个整数求和，并用链表形式返回结果。 
//
// 
//
// 示例： 
//
// 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
//输出：2 -> 1 -> 9，即912
// 
//
// 进阶：思考一下，假设这些数位是正向存放的，又该如何解决呢? 
//
// 示例： 
//
// 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
//输出：9 -> 1 -> 2，即912
// 
// Related Topics 递归 链表 数学 👍 104 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class addTwoNumbers2Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode num1 = l1, num2 = l2;
        ListNode res = new ListNode(-1);
        int carry = 0;
        while (num1 != null || num2 != null) {
            carry += num1 == null ? 0 : num1.val;
            carry += num2 == null ? 0 : num2.val;
            ListNode cur = new ListNode(carry % 10);
            carry /= 10;
            cur.next = res.next;
            res.next = cur;
            num1 = num1.next != null ? num1.next : null;
            num2 = num2.next != null ? num2.next : null;
        }
        return res.next;
    }

    public static void main(String[] args) {
        addTwoNumbersSolution solution = new addTwoNumbersSolution();
        System.out.println(
                solution.addTwoNumbers(
                        ListNode.buildLinkedList(6, 1, 7),
                        ListNode.buildLinkedList(2, 9, 5)
                )
        );
    }
}
//leetcode submit region end(Prohibit modification and deletion)
