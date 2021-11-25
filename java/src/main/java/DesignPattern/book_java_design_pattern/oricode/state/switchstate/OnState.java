//DesignPattern.book_java_design_pattern.oricode.state.switchstate.OnState.java
package DesignPattern.book_java_design_pattern.oricode.state.switchstate;

//��״̬��
public class OnState extends SwitchState {
    public void on(Switch s) {
        System.out.println("�Ѿ��򿪣�");
    }

    public void off(Switch s) {
        System.out.println("�رգ�");
        s.setState(Switch.getState("off"));
    }
}
