//DesignPattern.book_java_design_pattern.oricode.factorymethod.DatabaseLogger.java
package designpattern.book.oricode.factorymethod;

//���ݿ���־��¼�����䵱�����Ʒ��ɫ
public class DatabaseLogger implements Logger {
    public void writeLog() {
        System.out.println("���ݿ���־��¼��");
    }
}