package leetcode.editor.cn;//请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指
//向链表中的任意节点或者 null。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
// 
//
// 示例 3： 
//
// 
//
// 输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
// 
//
// 示例 4： 
//
// 输入：head = []
//输出：[]
//解释：给定的链表为空（空指针），因此返回 null。
// 
//
// 
//
// 提示： 
//
// 
// -10000 <= Node.val <= 10000 
// Node.random 为空（null）或指向链表中的节点。 
// 节点数目不超过 1000 。 
// 
//
// 
//
// 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-
//pointer/ 
//
// 
// Related Topics 哈希表 链表 👍 376 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


import java.util.HashMap;
import java.util.Map;

class copyRandomList2Solution {
    public static void main(String[] args) {
        copyRandomList2Solution solution = new copyRandomList2Solution();
    }

    public RandomNode copyRandomList(RandomNode head) {
        return getNodeByBuildNode(head);
    }

//    private RandomNode getNodeByBuildNode(RandomNode head) {
//        if (head == null)
//            return head;
//        // 新节点插入到旧节点后面
//        for (RandomNode tmp = head; tmp != null; tmp = tmp.next.next) {
//            RandomNode randomNode = new RandomNode(tmp.val);
//            randomNode.next = tmp.next;
//            tmp.next = randomNode;
//        }
//        // 建立random指针关系
//        for (RandomNode tmp = head; tmp != null; tmp = tmp.next.next) {
//            tmp.next.random = (tmp.random != null) ? tmp.random.next : null;
//        }
//        // 分离新旧节点
//        RandomNode newRandomNode = head.next;
//        for (RandomNode tmp = head; tmp != null; tmp = tmp.next) {
//            RandomNode newN = tmp.next;
//            tmp.next = newN.next;
//            newN.next = tmp.next.next != null ? tmp.next.next.next : null;
//        }
//        return newRandomNode;
//    }

    private RandomNode getNodeByBuildNode(RandomNode head) {
        if (head == null)
            return head;
        RandomNode cur = head;
        // 追加节点
        while (cur != null) {
            RandomNode node = new RandomNode(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }
        // 连接随机关系
        cur = head;
        while (cur.next.next != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        // 拆分链表
        cur = head;
        RandomNode res = cur.next;
        RandomNode pre = cur.next;
        while (cur.next.next != null) {
            cur.next = pre.next;
            pre.next = pre.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        return res;
    }

    private RandomNode getNodeByHashMap(RandomNode head) {
        Map<RandomNode, RandomNode> map = new HashMap<>();
        for (RandomNode tmp = head; tmp != null; tmp = tmp.next)
            map.put(tmp, new RandomNode(tmp.val));
        for (RandomNode tmp = head; tmp != null; tmp = tmp.next) {
            map.get(tmp).next = map.get(tmp.next);
            map.get(tmp).random = map.get(tmp.random);
        }
        return map.get(head);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
