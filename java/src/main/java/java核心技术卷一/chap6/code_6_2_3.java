package java核心技术卷一.chap6;

import java.util.function.BinaryOperator;

public class code_6_2_3 {
    public static void main(String[] args) {
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        processBinaryOperator(binaryOperator);
    }

    private static void processBinaryOperator(BinaryOperator<Integer> binaryOperator) {
        System.out.println(binaryOperator.apply(3, 5));
    }

}
