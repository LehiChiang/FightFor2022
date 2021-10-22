package java核心技术卷一.chap9;

import java.util.*;

public class code_9_3_3 {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        set.add(2);
        System.out.println(set);
        Deque<Integer> deque1 = new ArrayDeque<>();
        List<Integer> deque2 = new LinkedList<>();
    }
}
