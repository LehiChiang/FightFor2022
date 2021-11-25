//DesignPattern.book_java_design_pattern.oricode.factorymethod.Client.java
package DesignPattern.book_java_design_pattern.oricode.factorymethod;

public class Client {
    public static void main(String args[]) {
        LoggerFactory factory;
        Logger logger;
        //factory = new FileLoggerFactory(); //�����������ļ�ʵ��
        factory = (LoggerFactory) XMLUtil.getBean(); //getBean()�ķ�������ΪObject����Ҫ����ǿ������ת��

        logger = factory.createLogger();
        logger.writeLog();
    }
}