//DesignPattern.book_java_design_pattern.oricode.flyweight.simple.Client.java
package DesignPattern.book_java_design_pattern.oricode.flyweight.simple;

public class Client {
    public static void main(String args[]) {
        IgoChessman black1, black2, black3, white1, white2;
        IgoChessmanFactory factory;

        //��ȡ��Ԫ��������
        factory = IgoChessmanFactory.getInstance();

        //ͨ����Ԫ������ȡ���ź���
        black1 = factory.getIgoChessman("b");
        black2 = factory.getIgoChessman("b");
        black3 = factory.getIgoChessman("b");
        System.out.println("�ж����ź����Ƿ���ͬ��" + (black1 == black2));

        //ͨ����Ԫ������ȡ���Ű���
        white1 = factory.getIgoChessman("w");
        white2 = factory.getIgoChessman("w");
        System.out.println("�ж����Ű����Ƿ���ͬ��" + (white1 == white2));

        //��ʾ����
        black1.display();
        black2.display();
        black3.display();
        white1.display();
        white2.display();
    }
}