package designpattern.book.code.ch5.widget;

public class SpringComboBox extends ComboBox {
    @Override
    public void display() {
        System.out.println("春天主题：" + super.getWidgetName());
    }
}
