//DesignPattern.book_java_design_pattern.oricode.decorator.ComponentDecorator.java
package designpattern.book.oricode.decorator;

import java.awt.*;

public class ComponentDecorator extends Component {
    private Component component;  //ά�ֶԳ��󹹼����Ͷ��������

    //ע����󹹼����͵Ķ���
    public ComponentDecorator(Component component) {
        this.component = component;
    }

    public void display() {
        component.display();
    }
}