//DesignPattern.book_java_design_pattern.oricode.strategy.VIPDiscount.java
package designpattern.book.oricode.strategy;

//VIP��ԱƱ�ۿ��ࣺ���������
public class VIPDiscount implements Discount {
    private final double DISCOUNT = 0.5;

    public double calculate(double price) {
        System.out.println("VIPƱ��");
        System.out.println("���ӻ��֣�");
        return price * DISCOUNT;
    }
}