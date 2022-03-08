//DesignPattern.book_java_design_pattern.oricode.decorator.BlackBorderDecorator.java
package designpattern.book.oricode.decorator;

import java.awt.*;

public class BlackBorderDecorator extends ComponentDecorator {
    public BlackBorderDecorator(Component component) {
        super(component);
    }

    public void display() {
        this.setBlackBorder();
        super.display();
    }

    public void setBlackBorder() {
        System.out.println("Ϊ�������Ӻ�ɫ�߿�");
    }
}