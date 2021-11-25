//DesignPattern.book_java_design_pattern.oricode.templatemethod.Account.java
package DesignPattern.book_java_design_pattern.oricode.templatemethod;

//�˻��ࣺ������
public abstract class Account {
    //���������������巽��
    public boolean validate(String account, String password) {
        System.out.println("�˺ţ�" + account);
        System.out.println("���룺" + password);
        if (account.equalsIgnoreCase("���޼�") && password.equalsIgnoreCase("123456")) {
            return true;
        } else {
            return false;
        }
    }

    //���������������󷽷�
    public abstract void calculateInterest();

    //���������������巽��
    public void display() {
        System.out.println("��ʾ��Ϣ��");
    }

    //ģ�巽��
    public void handle(String account, String password) {
        if (!validate(account, password)) {
            System.out.println("�˻����������");
            return;
        }
        calculateInterest();
        display();
    }
}
