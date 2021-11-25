//DesignPattern.book_java_design_pattern.oricode.mediator.Component.java
package DesignPattern.book_java_design_pattern.oricode.mediator;

//��������ࣺ����ͬ����
public abstract class Component {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    //ת������
    public void changed() {
        mediator.componentChanged(this);
    }

    public abstract void update();
}