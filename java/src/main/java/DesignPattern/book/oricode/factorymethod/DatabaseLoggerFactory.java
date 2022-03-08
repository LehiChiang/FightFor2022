//DesignPattern.book_java_design_pattern.oricode.factorymethod.DatabaseLoggerFactory.java
package designpattern.book.oricode.factorymethod;

//���ݿ���־��¼�������࣬�䵱���幤����ɫ
public class DatabaseLoggerFactory implements LoggerFactory {
    public Logger createLogger() {
        //�������ݿ⣬����ʡ��
        //�������ݿ���־��¼������
        Logger logger = new DatabaseLogger();
        //��ʼ�����ݿ���־��¼��������ʡ��
        return logger;
    }
}