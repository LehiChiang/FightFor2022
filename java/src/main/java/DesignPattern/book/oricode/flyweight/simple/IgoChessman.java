//DesignPattern.book_java_design_pattern.oricode.flyweight.simple.IgoChessman.java
package designpattern.book.oricode.flyweight.simple;

//Χ�������ࣺ������Ԫ��
public abstract class IgoChessman {
    public abstract String getColor();

    public void display() {
        System.out.println("������ɫ��" + this.getColor());
    }
}
