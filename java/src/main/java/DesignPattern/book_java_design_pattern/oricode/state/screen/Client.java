//DesignPattern.book_java_design_pattern.oricode.state.screen.Client.java
package DesignPattern.book_java_design_pattern.oricode.state.screen;

public class Client {
    public static void main(String args[]) {
        Screen screen = new Screen();
        screen.onClick();
        screen.onClick();
        screen.onClick();
    }
}