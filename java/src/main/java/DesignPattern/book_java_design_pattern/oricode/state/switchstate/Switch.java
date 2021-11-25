//DesignPattern.book_java_design_pattern.oricode.state.switchstate.Switch.java
package DesignPattern.book_java_design_pattern.oricode.state.switchstate;

public class Switch {
    private static SwitchState currentState, onState, offState; //����������̬��״̬����
    private String name;

    public Switch(String name) {
        this.name = name;
        onState = new OnState();
        offState = new OffState();
        currentState = onState;
    }

    public static SwitchState getState(String type) {
        if (type.equalsIgnoreCase("on")) {
            return onState;
        } else {
            return offState;
        }
    }

    public void setState(SwitchState state) {
        currentState = state;
    }

    //�򿪿���
    public void on() {
        System.out.print(name);
        currentState.on(this);
    }

    //�رտ���
    public void off() {
        System.out.print(name);
        currentState.off(this);
    }
}
