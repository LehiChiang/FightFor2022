package leetcode.editor.datastructure;

/**
 * @author chestnut
 * @creat 2021-08-06 8:36
 */

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode buildLinkedList(int[] nums) {
        ListNode linkList = new ListNode(-1);
        ListNode pointer = linkList;
        for (int num : nums) {
            pointer.next = new ListNode(num);
            pointer = pointer.next;
        }
        return linkList.next;
    }

    public static ListNode buildLinkedListWithHead(int[] nums) {
        ListNode linkList = new ListNode(-1);
        ListNode pointer = linkList;
        for (int num : nums) {
            pointer.next = new ListNode(num);
            pointer = pointer.next;
        }
        return linkList;
    }

    public static void printLinkedList(ListNode linkList) {
        ListNode pointer = linkList;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (pointer != null) {
            sb.append(pointer.val).append(',');
            pointer = pointer.next;
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        return "{" + val + ", " + next + '}';
    }
}
