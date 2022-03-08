package designpattern.book.code.ch5.widget;

public class SummerButton extends Button {
    @Override
    public void display() {
        System.out.println("夏天主题：" + super.getWidgetName());
    }
}
