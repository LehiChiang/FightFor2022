//DesignPattern.book_java_design_pattern.oricode.command.ExitCommand.java
package designpattern.book.oricode.command;

public class ExitCommand extends Command {
    private SystemExitClass seObj;  //ά�ֶ���������ߵ�����

    public ExitCommand() {
        seObj = new SystemExitClass();
    }

    //����ִ�з�������������������ߵ�ҵ�񷽷�
    public void execute() {
        seObj.exit();
    }
}