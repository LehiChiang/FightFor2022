//DesignPattern.book_java_design_pattern.oricode.command.Client.java
package DesignPattern.book_java_design_pattern.oricode.command;

public class Client {
    public static void main(String args[]) {
        FunctionButton fb = new FunctionButton();
        Command command; //�����������
        command = (Command) XMLUtil.getBean(); //��ȡ�����ļ����������ɶ���

        fb.setCommand(command); //���������ע�빦�ܼ�
        fb.click(); //���ù��ܼ���ҵ�񷽷�
    }
}