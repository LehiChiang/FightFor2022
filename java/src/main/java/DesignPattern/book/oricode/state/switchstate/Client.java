//DesignPattern.book_java_design_pattern.oricode.state.switchstate.Client.java
package designpattern.book.oricode.state.switchstate;

public class Client {
    public static void main(String args[]) {
        Switch s1, s2;
        s1 = new Switch("����1");
        s2 = new Switch("����2");

        s1.on();
        s2.on();
        s1.off();
        s2.off();
        s2.on();
        s1.on();
    }
}
