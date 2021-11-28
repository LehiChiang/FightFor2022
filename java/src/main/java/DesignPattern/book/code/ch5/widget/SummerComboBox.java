package designpattern.book.code.ch5.widget;

public class SummerComboBox extends ComboBox {
    @Override
    public void display() {
        System.out.println("夏天主题：" + super.getWidgetName());
    }
}
