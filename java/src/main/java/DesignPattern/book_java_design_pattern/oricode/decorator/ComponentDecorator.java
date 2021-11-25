//DesignPattern.book_java_design_pattern.oricode.decorator.ComponentDecorator.java
package DesignPattern.book_java_design_pattern.oricode.decorator;

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