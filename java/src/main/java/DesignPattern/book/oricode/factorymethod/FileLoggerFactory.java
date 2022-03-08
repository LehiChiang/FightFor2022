//DesignPattern.book_java_design_pattern.oricode.factorymethod.FileLoggerFactory.java
package designpattern.book.oricode.factorymethod;

//�ļ���־��¼�������࣬�䵱���幤����ɫ
public class FileLoggerFactory implements LoggerFactory {
    public Logger createLogger() {
        //�����ļ���־��¼������
        Logger logger = new FileLogger();
        //�����ļ�������ʡ��
        return logger;
    }
}