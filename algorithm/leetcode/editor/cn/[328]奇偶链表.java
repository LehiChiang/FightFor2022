package leetcode.editor.cn;
//给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
// 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
// 示例 1:
// 输入: 1->2->3->4->NULL
//输出: 1->3->2->NULL
// 示例 2:
// 输入: 2->1->3->5->6->4->7->NULL 
//输出: 2->3->6->7->1->5->4->NULL
// 说明:
// 应当保持奇数节点和偶数节点的相对顺序。 
// 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
// Related Topics 链表 
// 👍 451 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

class oddEvenListSolution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return head;
        ListNode oddHead = head, evenHead = head.next, res = new ListNode(-1);
        ListNode even = evenHead;
        res.next = oddHead;
        while (evenHead != null && evenHead.next != null) {
            oddHead.next = evenHead.next != null ? evenHead.next : evenHead;
            evenHead.next = oddHead.next.next;
            oddHead = oddHead.next;
            evenHead = evenHead.next;
        }
        oddHead.next = even;
        return res.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
