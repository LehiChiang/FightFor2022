//DesignPattern.book_java_design_pattern.oricode.state.RestrictedState.java
package designpattern.book.oricode.state;

//����״̬������״̬��
public class RestrictedState extends AccountState {
    public RestrictedState(AccountState state) {
        this.acc = state.acc;
    }

    public void deposit(double amount) {
        acc.setBalance(acc.getBalance() + amount);
        stateCheck();
    }

    public void withdraw(double amount) {
        System.out.println("�ʺ����ޣ�ȡ��ʧ��");
    }

    public void computeInterest() {
        System.out.println("������Ϣ��");
    }

    //״̬ת��
    public void stateCheck() {
        if (acc.getBalance() > 0) {
            acc.setState(new NormalState(this));
        } else if (acc.getBalance() > -2000) {
            acc.setState(new OverdraftState(this));
        }
    }
}
