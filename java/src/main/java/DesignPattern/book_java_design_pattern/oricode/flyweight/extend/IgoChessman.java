//DesignPattern.book_java_design_pattern.oricode.flyweight.extend.IgoChessman.java
package DesignPattern.book_java_design_pattern.oricode.flyweight.extend;

//Χ�������ࣺ������Ԫ��
public abstract class IgoChessman {
    public abstract String getColor();

    public void display(Coordinates coord) {
        System.out.println("������ɫ��" + this.getColor() + "������λ�ã�" + coord.getX() + "��" + coord.getY());
    }
}

