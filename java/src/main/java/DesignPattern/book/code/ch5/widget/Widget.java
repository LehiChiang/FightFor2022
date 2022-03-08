package designpattern.book.code.ch5.widget;

public abstract class Widget {

    public String getWidgetName() {
        return this.getClass().getName();
    }

    public void display() {
        System.out.println(getWidgetName());
    }

    @Override
    public String toString() {
        return getWidgetName();
    }
}
