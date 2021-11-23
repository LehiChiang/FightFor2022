package java核心技术卷一.chap9;

import java.util.Set;
import java.util.TreeSet;

public class code_9_3_3 {
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        set.add("Jiang");
        set.add("Jiang");
        set.add("Jiang");
        set.add("Liu");
        set.add("Annie");
        System.out.println(set);
    }
}
