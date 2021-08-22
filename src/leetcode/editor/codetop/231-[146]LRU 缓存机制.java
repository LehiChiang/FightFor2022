package leetcode.editor.codetop;
//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
// 实现 LRUCache 类：
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
// 示例：
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
// 提示：
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put
// Related Topics 设计 哈希表 链表 双向链表 👍 1571 👎 0


import java.util.HashMap;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    class DoubleListNode {
        int key;
        int value;
        DoubleListNode pre;
        DoubleListNode next;

        DoubleListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, DoubleListNode> map;
    DoubleListNode head, tail;
    int cap;
    int size;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new DoubleListNode(-1, -1);
        tail = new DoubleListNode(-1, -1);
        head.next = tail;
        tail.pre = head;
        cap = capacity;
        size = 0;
    }

    public int get(int key) {
        DoubleListNode node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            moveToHead(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        DoubleListNode node = map.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            node = new DoubleListNode(key, value);
            map.put(key, node);
            addToHead(node);
            ++size;
            if (size > cap) {
                DoubleListNode tail = removeTail();
                map.remove(tail.key);
                --size;
            }
        }
    }

    private void moveToHead(DoubleListNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(DoubleListNode node) {
        node.pre = head;
        head.next.pre = node;
        node.next = head.next;
        head.next = node;
    }

    private void removeNode(DoubleListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private DoubleListNode removeTail() {
        DoubleListNode tailNode = tail.pre;
        removeNode(tailNode);
        return tailNode;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
