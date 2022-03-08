//DesignPattern.book_java_design_pattern.oricode.interpreter.Client.java
package designpattern.book.oricode.interpreter;

public class Client {
    public static void main(String args[]) {
        String instruction = "down run 10 and left move 20";
        InstructionHandler handler = new InstructionHandler();
        handler.handle(instruction);
        String outString;
        outString = handler.output();
        System.out.println(outString);
    }
}
