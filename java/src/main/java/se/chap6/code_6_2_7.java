package se.chap6;

import java.util.function.IntConsumer;

public class code_6_2_7 {
    public static void main(String[] args) {
        repeat(System.out::println);
    }

    private static void repeat(IntConsumer consumer) {
        for (int i = 0; i < 10; i++)
            consumer.accept(i < 5 ? i * 100 : i);
    }
}


