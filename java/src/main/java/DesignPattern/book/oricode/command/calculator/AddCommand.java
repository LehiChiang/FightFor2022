//DesignPattern.book_java_design_pattern.oricode.command.calculator.AddCommand;
package designpattern.book.oricode.command.calculator;

//����������
public class AddCommand extends AbstractCommand {
    private Adder adder = new Adder();
    private int value;

    //ʵ�ֳ�����������������execute()���������üӷ���ļӷ�����
    public int execute(int value) {
        this.value = value;
        return adder.add(value);
    }

    //ʵ�ֳ�����������������undo()������ͨ����һ���෴����ʵ�ּӷ����������
    public int undo() {
        return adder.add(-value);
    }
}