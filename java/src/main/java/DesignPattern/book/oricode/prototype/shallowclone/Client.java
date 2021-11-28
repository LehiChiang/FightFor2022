//DesignPattern.book_java_design_pattern.oricode.prototype.shallowclone.Client.java
package designpattern.book.oricode.prototype.shallowclone;

public class Client {
    public static void main(String args[]) {
        WeeklyLog log_previous, log_new;
        log_previous = new WeeklyLog();            //����ԭ�Ͷ���
        Attachment attachment = new Attachment();    //������������
        log_previous.setAttachment(attachment);       //��������ӵ��ܱ���
        log_new = log_previous.clone();             //���ÿ�¡����������¡����
        //�Ƚ��ܱ�
        System.out.println("�ܱ��Ƿ���ͬ�� " + (log_previous == log_new));
        //�Ƚϸ���
        System.out.println("�����Ƿ���ͬ�� " + (log_previous.getAttachment() == log_new.getAttachment()));
    }
}