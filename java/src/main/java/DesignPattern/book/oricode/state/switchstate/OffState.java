//DesignPattern.book_java_design_pattern.oricode.state.switchstate.OffState.java
package designpattern.book.oricode.state.switchstate;

//�ر�״̬��
public class OffState extends SwitchState {
    public void on(Switch s) {
        System.out.println("�򿪣�");
        s.setState(Switch.getState("on"));
    }

    public void off(Switch s) {
        System.out.println("�Ѿ��رգ�");
    }
}