//DesignPattern.book_java_design_pattern.oricode.decorator.ScrollBarDecorator.java
package designpattern.book.oricode.decorator;

import java.awt.*;

public class ScrollBarDecorator extends ComponentDecorator {
    public ScrollBarDecorator(Component component) {
        super(component);
    }

    public void display() {
        this.setScrollBar();
        super.display();
    }

    public void setScrollBar() {
        System.out.println("Ϊ�������ӹ�������");
    }
}