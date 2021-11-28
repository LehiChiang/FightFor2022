package designpattern.book.code.ch5.widget;

public class SpringTextField extends TextField {
    @Override
    public void display() {
        System.out.println("春天主题：" + super.getWidgetName());
    }
}
