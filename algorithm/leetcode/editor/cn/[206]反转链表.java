package leetcode.editor.cn;
//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 示例 1：
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 示例 2：
//输入：head = [1,2]
//输出：[2,1]
// 示例 3：
//输入：head = []
//输出：[]
// 提示：
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
// Related Topics 递归 链表 👍 1908 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import leetcode.editor.datastructure.ListNode;

import static leetcode.editor.datastructure.ListNode.buildLinkedList;
import static leetcode.editor.datastructure.ListNode.printLinkedList;

class reverseListSolution {
    // 迭代法一
//    public ListNode reverseList(ListNode head) {
//        ListNode newHead = new ListNode(-1), p = newHead.next;
//        ListNode headp;
//        while (head != null) {
//            headp = head;
//            head = head.next;
//            headp.next = p;
//            p = headp;
//            newHead.next = p;
//        }
//        return newHead.next;
//    }
    // 迭代法二
//    public ListNode reverseList(ListNode head) {
//        ListNode pre = null;
//        ListNode cur = head;
//        while (cur != null) {
//            ListNode node = cur;
//            cur = cur.next;
//            node.next = pre;
//            pre = node;
//        }
//        return pre;
//    }

    // 递归法一
//    public ListNode reverseList(ListNode head) {
//        if (head == null || head.next == null)
//            return head;
//        ListNode newNode = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return newNode;
//    }

    // 递归法二
    public ListNode reverseList(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null)
            return pre;
        ListNode nextNode = cur.next;
        cur.next = pre;
        pre = cur;
        cur = nextNode;
        return reverse(pre, cur);
    }

    public static void main(String[] args) {
        reverseListSolution solution = new reverseListSolution();
        ListNode res = solution.reverseList(buildLinkedList(new int[]{1,2,3,4,5,6}));
        printLinkedList(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
