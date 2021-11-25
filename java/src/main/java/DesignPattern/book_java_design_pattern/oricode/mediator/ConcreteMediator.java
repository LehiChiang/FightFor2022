//DesignPattern.book_java_design_pattern.oricode.mediator.ConcreteMediator.java
package DesignPattern.book_java_design_pattern.oricode.mediator;

//�����н���
public class ConcreteMediator extends Mediator {
    //ά�ֶԸ���ͬ�¶��������
    public Button addButton;
    public List list;
    public TextBox userNameTextBox;
    public ComboBox cb;

    //��װͬ�¶���֮��Ľ���
    public void componentChanged(Component c) {
        //������ť
        if (c == addButton) {
            System.out.println("--�������Ӱ�ť--");
            list.update();
            cb.update();
            userNameTextBox.update();
        }
        //���б��ѡ��ͻ�
        else if (c == list) {
            System.out.println("--���б��ѡ��ͻ�--");
            cb.select();
            userNameTextBox.setText();
        }
        //����Ͽ�ѡ��ͻ�
        else if (c == cb) {
            System.out.println("--����Ͽ�ѡ��ͻ�--");
            cb.select();
            userNameTextBox.setText();
        }
    }
}
