//DesignPattern.book_java_design_pattern.oricode.state.Account.java
package designpattern.book.oricode.state;

//�����˻���������
public class Account {
    private AccountState state; //ά��һ���Գ���״̬���������
    private String owner; //������
    private double balance = 0; //�˻����

    public Account(String owner, double init) {
        this.owner = owner;
        this.balance = balance;
        this.state = new NormalState(this); //���ó�ʼ״̬
        System.out.println(this.owner + "��������ʼ���Ϊ" + init);
        System.out.println("---------------------------------------------");
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setState(AccountState state) {
        this.state = state;
    }

    public void deposit(double amount) {
        System.out.println(this.owner + "���" + amount);
        state.deposit(amount); //����״̬�����deposit()����
        System.out.println("�������Ϊ" + this.balance);
        System.out.println("�����ʻ�״̬Ϊ" + this.state.getClass().getName());
        System.out.println("---------------------------------------------");
    }

    public void withdraw(double amount) {
        System.out.println(this.owner + "ȡ��" + amount);
        state.withdraw(amount); //����״̬�����withdraw()����
        System.out.println("�������Ϊ" + this.balance);
        System.out.println("�����ʻ�״̬Ϊ" + this.state.getClass().getName());
        System.out.println("---------------------------------------------");
    }

    public void computeInterest() {
        state.computeInterest(); //����״̬�����computeInterest()����
    }
}
