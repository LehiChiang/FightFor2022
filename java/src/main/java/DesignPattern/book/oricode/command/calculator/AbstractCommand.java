//DesignPattern.book_java_design_pattern.oricode.command.calculator.AbstractCommand.java
package designpattern.book.oricode.command.calculator;

//����������
public abstract class AbstractCommand {
    public abstract int execute(int value); //��������ִ�з���execute()

    public abstract int undo(); //������������undo()
}

