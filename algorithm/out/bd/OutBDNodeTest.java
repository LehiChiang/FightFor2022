package out.bd;

import datastructure.ListNode;
import org.junit.jupiter.api.Test;

class OutBDNodeTest {

    @Test
    void midOrLowMidNode() {
        ListNode res = OutBDNode.MidOrLowMidNode(ListNode.buildLinkedList(1));
        ListNode.printLinkedList(res);
    }
}