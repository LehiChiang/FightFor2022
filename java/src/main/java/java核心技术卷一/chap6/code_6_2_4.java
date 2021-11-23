package java核心技术卷一.chap6;

public class code_6_2_4 {
    public static void main(String[] args) {
        Runnable task = System.out::println;
        task.run();
    }
}
