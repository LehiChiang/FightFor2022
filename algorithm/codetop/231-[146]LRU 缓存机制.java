package codetop;
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
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    class DoubleLinkedList {
        int key; //双链表中加入键的原因是方便在map中获得该节点
        int val;
        DoubleLinkedList preNode;
        DoubleLinkedList nextNode;

        public DoubleLinkedList() {}

        public DoubleLinkedList(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, DoubleLinkedList> map;
    DoubleLinkedList head;
    DoubleLinkedList tail;
    int capacity;
    int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new DoubleLinkedList();
        tail = new DoubleLinkedList();
        head.nextNode = tail;
        tail.preNode = head;
        this.size = 0;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            moveToHead(map.get(key));
            return map.get(key).val;
        } else
            return -1;
    }

    public void put(int key, int value) {
        DoubleLinkedList node = map.get(key);
        if (node == null) {
            DoubleLinkedList newNode = new DoubleLinkedList(key, value);
            map.put(key, newNode);
            insertToHead(newNode);
            size++;
            if (size > capacity) {
                DoubleLinkedList deletedNode = deleteFromTail();
                map.remove(deletedNode.key);
                size--;
            }
        }
        else {
            moveToHead(node);
            node.val = value;
        }
    }

    private DoubleLinkedList deleteFromTail() {
        DoubleLinkedList delete = tail.preNode;
        deleteNode(delete);
        return delete;
    }

    private void deleteNode(DoubleLinkedList delete) {
        delete.nextNode.preNode = delete.preNode;
        delete.preNode.nextNode = delete.nextNode;
    }


    private void insertToHead(DoubleLinkedList newNode) {
        newNode.nextNode = head.nextNode;
        head.nextNode.preNode = newNode;
        head.nextNode = newNode;
        head.nextNode.preNode = head;
    }

    private void moveToHead(DoubleLinkedList doubleLinkedList) {
        deleteNode(doubleLinkedList);
        insertToHead(doubleLinkedList);
    }

    @Override
    public String toString() {
        DoubleLinkedList list = head.nextNode;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LRUCache: ");
        while (list != tail) {
            stringBuilder.append("[" + list.key + "->" + list.val + "]");
            list = list.nextNode;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,1);
        System.out.println(lruCache);
        lruCache.put(2,2);
        System.out.println(lruCache);
        lruCache.put(3,3);
        System.out.println(lruCache);
        lruCache.get(1);
        System.out.println(lruCache);
        lruCache.put(2, 5);
        System.out.println(lruCache);
        lruCache.put(4,4);
        System.out.println(lruCache);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
