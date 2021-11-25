//DesignPattern.book_java_design_pattern.oricode.decorator.ScrollBarDecorator.java
package DesignPattern.book_java_design_pattern.oricode.decorator;

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