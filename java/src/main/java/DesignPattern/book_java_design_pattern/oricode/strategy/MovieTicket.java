//DesignPattern.book_java_design_pattern.oricode.strategy.MovieTicket.java
package DesignPattern.book_java_design_pattern.oricode.strategy;

//��ӰƱ�ࣺ������
public class MovieTicket {
    private double price;
    private Discount discount; //ά��һ���Գ����ۿ��������

    //ע��һ���ۿ������
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double getPrice() {
        //�����ۿ�����ۿۼۼ��㷽��
        return discount.calculate(this.price);
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
