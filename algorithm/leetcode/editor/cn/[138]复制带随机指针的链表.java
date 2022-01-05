package leetcode.editor.cn;//给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
//
// 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 
//指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。 
//
// 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random 
//--> y 。 
//
// 返回复制链表的头节点。 
//
// 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示： 
//
// 
// val：一个表示 Node.val 的整数。 
// random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为 null 。 
// 
//
// 你的代码 只 接受原链表的头节点 head 作为传入参数。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
// 
//
// 示例 4： 
//
// 
//输入：head = []
//输出：[]
//解释：给定的链表为空（空指针），因此返回 null。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 1000 
// -10000 <= Node.val <= 10000 
// Node.random 为空（null）或指向链表中的节点。 
// 
// Related Topics 哈希表 链表 👍 789 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class RandomNode {
    int val;
    RandomNode next;
    RandomNode random;

    public RandomNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class copyRandomListSolution {
    public RandomNode copyRandomList(RandomNode head) {
        return getNodeInPlace(head);
    }

    public RandomNode getNodeInPlace(RandomNode head) {
        if (head == null)
            return head;
        // 将复制的节点放置在源节点的后面 A -> A' -> B -> B'
        for (RandomNode randomNode = head; randomNode != null; randomNode = randomNode.next.next) {
            RandomNode newRandomNode = new RandomNode(randomNode.val);
            newRandomNode.next = randomNode.next;
            randomNode.next = newRandomNode;
        }
        // 把random域串起来
        for (RandomNode randomNode = head; randomNode != null; randomNode = randomNode.next.next)
            randomNode.next.random = (randomNode.random != null) ? randomNode.random.next : null;
        // 把源节点，复制节点分开成两个链表
        RandomNode newhead = head.next;
        for (RandomNode randomNode = head; randomNode != null; randomNode = randomNode.next) {
            RandomNode newRandomNode = randomNode.next;
            randomNode.next = randomNode.next.next;
            newRandomNode.next = (newRandomNode.next != null) ? newRandomNode.next.next : null;
        }
        return newhead;
    }

    private RandomNode getNodeByHashMap(RandomNode head) {
        if (head == null)
            return head;
        Map<RandomNode, RandomNode> map = new HashMap<>();
        RandomNode randomNode = head;
        for (; randomNode != null; randomNode = randomNode.next) {
            map.put(randomNode, new RandomNode(randomNode.val));
        }
        randomNode = head;
        for (; randomNode != null; randomNode = randomNode.next) {
            map.get(randomNode).next = map.get(randomNode.next);
            map.get(randomNode).random = map.get(randomNode.random);
        }
        return map.get(head);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
