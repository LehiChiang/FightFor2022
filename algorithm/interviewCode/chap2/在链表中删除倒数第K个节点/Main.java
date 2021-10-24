package interviewCode.chap2.在链表中删除倒数第K个节点;

import java.util.Scanner;

class RemoveLastKthNode {
    public static Node removeLastKthNode(Node head, int k) {
        if (head == null || k < 1) {
            return head;
        }
        Node cur = head;
        while(cur != null) {
            k--;
            cur = cur.next;
        }
        if (k == 0) {
            head = head.next;
        }
        if (k < 0) {
            cur = head;
            while (++k != 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }
}

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String[] s1 = scanner.nextLine().split(" ");
        String[] s2 = scanner.nextLine().split(" ");
        int n = Integer.parseInt(s1[0]);
        int k = Integer.parseInt(s1[1]);
        Node head = Node.createNodeList(s2);
        head = RemoveLastKthNode.removeLastKthNode(head, k);
        Node.printNodeList(head);
    }
}

class Node {

    Node next;
    int value;

    public Node(int value) {
        this.value = value;
    }

    public static Node createNodeList(String[] values) {
        Node head = new Node(Integer.parseInt(values[0]));
        Node node = head;
        for (int i = 1; i < values.length; i++) {
            Node newNode = new Node(Integer.parseInt(values[i]));
            node.next = newNode;
            node = newNode;
        }
        return head;
    }

    public static void printNodeList(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.value).append(" ");
            head = head.next;
        }
        System.out.println(sb);
    }
}
