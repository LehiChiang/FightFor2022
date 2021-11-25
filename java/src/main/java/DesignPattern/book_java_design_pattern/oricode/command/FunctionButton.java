//DesignPattern.book_java_design_pattern.oricode.command.FunctionButton.java
package DesignPattern.book_java_design_pattern.oricode.command;

public class FunctionButton {
    private Command command;  //ά��һ������������������

    //Ϊ���ܼ�ע������
    public void setCommand(Command command) {
        this.command = command;
    }

    //��������ķ���
    public void click() {
        System.out.print("�������ܼ�: ");
        command.execute();
    }
}
