//DesignPattern.book_java_design_pattern.oricode.templatemethod.hookmethod.Client.java
package designpattern.book.oricode.templatemethod.hookmethod;

public class Client {
    public static void main(String args[]) {
        DataViewer dv;
        dv = new XMLDataViewer();
        dv.process();
    }
}
