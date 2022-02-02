package leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import datastructure.ListNode;

import java.util.PriorityQueue;

class mergeKListsOffer2Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode node : lists) {
            if (node != null)
                queue.offer(node);
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode pointer = dummyNode;
        while (!queue.isEmpty()) {
            ListNode minNode = queue.poll();
            pointer.next = minNode;
            minNode = minNode.next;
            pointer = pointer.next;
            if (minNode != null)
                queue.offer(minNode);
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        mergeKListsOffer2Solution solution = new mergeKListsOffer2Solution();
        ListNode[] list = new ListNode[3];
        list[0] = ListNode.buildLinkedList(1, 4, 5);
        list[1] = ListNode.buildLinkedList(1, 3, 4);
        list[2] = ListNode.buildLinkedList(2, 6);
        System.out.println(solution.mergeKLists(list));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
