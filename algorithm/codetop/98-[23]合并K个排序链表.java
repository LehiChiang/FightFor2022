package codetop;

import datastructure.ListNode;

import java.util.PriorityQueue;

class mergeKListsSolution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        ListNode res = new ListNode(-1);
        ListNode pre = res;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (nodeA, nodeB) -> nodeA.val - nodeB.val);
        for (ListNode list : lists) {
            if (list!=null)
                queue.add(list);
        }
        while (!queue.isEmpty()) {
            ListNode curList = queue.poll();
            pre.next = curList;
            pre = pre.next;
            curList = curList.next;
            if (curList != null)
                queue.add(curList);
        }
        return res.next;
    }

    public static void main(String[] args) {
        mergeKListsSolution solution = new mergeKListsSolution();
        ListNode res = solution.mergeKLists(new ListNode[]{
                ListNode.buildLinkedList(new int[]{1,4,5}, false),
                ListNode.buildLinkedList(new int[]{1,3,4}, false),
                ListNode.buildLinkedList(new int[]{2,6}, false)
        });
        System.out.println(res);
    }
}
