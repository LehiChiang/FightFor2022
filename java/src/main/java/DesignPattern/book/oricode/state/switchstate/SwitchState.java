//DesignPattern.book_java_design_pattern.oricode.state.switchstate.SwitchState.java
package designpattern.book.oricode.state.switchstate;

public abstract class SwitchState {
    public abstract void on(Switch s);

    public abstract void off(Switch s);
}