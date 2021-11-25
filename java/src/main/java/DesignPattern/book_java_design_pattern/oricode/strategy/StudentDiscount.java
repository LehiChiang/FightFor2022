//DesignPattern.book_java_design_pattern.oricode.strategy.StudentDiscount.java
package DesignPattern.book_java_design_pattern.oricode.strategy;

//ѧ��Ʊ�ۿ��ࣺ���������
public class StudentDiscount implements Discount {
    private final double DISCOUNT = 0.8;

    public double calculate(double price) {
        System.out.println("ѧ��Ʊ��");
        return price * DISCOUNT;
    }
}
