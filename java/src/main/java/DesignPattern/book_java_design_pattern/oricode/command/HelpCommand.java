//DesignPattern.book_java_design_pattern.oricode.command.HelpCommand.java
package DesignPattern.book_java_design_pattern.oricode.command;

public class HelpCommand extends Command {
    private DisplayHelpClass hcObj;   //ά�ֶ���������ߵ�����

    public HelpCommand() {
        hcObj = new DisplayHelpClass();
    }

    //����ִ�з�������������������ߵ�ҵ�񷽷�
    public void execute() {
        hcObj.display();
    }
}
