//DesignPattern.book_java_design_pattern.oricode.templatemethod.Client.java
package designpattern.book.oricode.templatemethod;

public class Client {
    public static void main(String args[]) {
        Account account;
        account = (Account) XMLUtil.getBean();
        account.handle("���޼�", "123456");
    }
}