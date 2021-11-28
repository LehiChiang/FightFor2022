//DesignPattern.book_java_design_pattern.oricode.command.calculator.CalculatorForm.java
package designpattern.book.oricode.command.calculator;

//�����������ࣺ��������
public class CalculatorForm {
    private AbstractCommand command;

    public void setCommand(AbstractCommand command) {
        this.command = command;
    }

    //������������execute()����ִ������
    public void compute(int value) {
        int i = command.execute(value);
        System.out.println("ִ�����㣬������Ϊ��" + i);
    }

    //������������undo()����ִ�г���
    public void undo() {
        int i = command.undo();
        System.out.println("ִ�г�����������Ϊ��" + i);
    }
}
