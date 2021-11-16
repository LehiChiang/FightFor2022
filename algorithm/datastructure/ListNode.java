package datastructure;

/**
 * @author chestnut
 */

public class ListNode {
    public int val;
    public ListNode next;

    /**
     * 尾节点构造器
     * @param val 节点值
     */
    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 链表节点构造器
     * @param val 节点值
     * @param next 指向的下一个节点
     */
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 无参构造器
     */
    public ListNode() {

    }

    /**
     * 构造链表
     * @param nums 构造链表的数组
     * @param withHead 是否需要头节点
     * @return 构造完成的链表
     */
    public static ListNode buildLinkedList(int[] nums, boolean withHead) {
        ListNode linkList = new ListNode(-1);
        ListNode pointer = linkList;
        for (int num : nums) {
            pointer.next = new ListNode(num);
            pointer = pointer.next;
        }
        return withHead ? linkList : linkList.next;
    }

    /**
     * 打印链表
     * @param linkList 链表
     */
    public static void printLinkedList(ListNode linkList) {
        ListNode pointer = linkList;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (pointer != null) {
            sb.append(pointer.val).append(',');
            pointer = pointer.next;
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb);
    }

    /**
     * 打印节点信息
     * @return 节点信息字符串
     */
    @Override
    public String toString() {
        return "{" + val + ", " + next + '}';
    }
}
