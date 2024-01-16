package out.bd;

import datastructure.ListNode;
import org.junit.jupiter.api.Test;

class OutBDNodeTest {

    @Test
    void midOrLowMidNode() {
        ListNode res = OutBDNode.MidOrLowMidNode(ListNode.buildLinkedList(1, 2, 3));
        ListNode.printLinkedList(res);
    }

    @Test
    void midOrUpMidNode() {
        ListNode res = OutBDNode.MidOrUpMidNode(ListNode.buildLinkedList(1, 2, 3, 4));
        ListNode.printLinkedList(res);
    }

    @Test
    void midOrUpMidPreNode() {
        ListNode listNode = ListNode.buildLinkedList(new int[]{1, 2, 3, 4, 5}, true);
        ListNode.printLinkedList(OutBDNode.MidOrUpMidPreNode(listNode));
    }

    @Test
    void midOrDownMidPreNode() {
        ListNode listNode = ListNode.buildLinkedList(new int[]{1}, true);
        ListNode.printLinkedList(OutBDNode.MidOrDownMidPreNode(listNode));
    }
}