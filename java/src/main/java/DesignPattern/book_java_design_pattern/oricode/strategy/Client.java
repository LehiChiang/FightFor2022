//DesignPattern.book_java_design_pattern.oricode.strategy.Client.java
package DesignPattern.book_java_design_pattern.oricode.strategy;

public class Client {
    public static void main(String args[]) {
        MovieTicket mt = new MovieTicket();
        double originalPrice = 60.0;
        double currentPrice;

        mt.setPrice(originalPrice);
        System.out.println("ԭʼ��Ϊ��" + originalPrice);
        System.out.println("---------------------------------");

        Discount discount;
        discount = (Discount) XMLUtil.getBean(); //��ȡ�����ļ����������ɾ����ۿ۶���
        mt.setDiscount(discount); //ע���ۿ۶���

        currentPrice = mt.getPrice();
        System.out.println("�ۺ��Ϊ��" + currentPrice);
    }
}
