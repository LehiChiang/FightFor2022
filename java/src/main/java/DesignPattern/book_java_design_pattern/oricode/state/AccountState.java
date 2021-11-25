//DesignPattern.book_java_design_pattern.oricode.state.AccountState.java
package DesignPattern.book_java_design_pattern.oricode.state;

//����״̬��
public abstract class AccountState {
    protected Account acc;

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract void computeInterest();

    public abstract void stateCheck();
}