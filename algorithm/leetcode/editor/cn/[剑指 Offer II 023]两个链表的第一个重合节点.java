package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class getIntersectionNodeOffer2Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA != null ? nodeA.next : headB;
            nodeB = nodeB != null ? nodeB.next : headA;
        }
        return nodeA;
    }

    public static void main(String[] args) {
        getIntersectionNodeOffer2Solution solution = new getIntersectionNodeOffer2Solution();
        System.out.println(solution.getIntersectionNode(ListNode.buildLinkedList(4,1,8,4,5), ListNode.buildLinkedList(5,0,1,8,4,5)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
