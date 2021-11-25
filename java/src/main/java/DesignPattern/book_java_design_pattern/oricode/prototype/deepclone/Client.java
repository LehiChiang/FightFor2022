//DesignPattern.book_java_design_pattern.oricode.prototype.deepclone.Client.java
package DesignPattern.book_java_design_pattern.oricode.prototype.deepclone;

public class Client {
    public static void main(String args[]) {
        WeeklyLog log_previous, log_new = null;
        log_previous = new WeeklyLog();           //����ԭ�Ͷ���
        Attachment attachment = new Attachment();   //������������
        log_previous.setAttachment(attachment);     //��������ӵ��ܱ���
        try {
            log_new = log_previous.deepClone(); //�������¡����������¡����
        } catch (Exception e) {
            System.err.println("��¡ʧ�ܣ�");
        }
        //�Ƚ��ܱ�
        System.out.println("�ܱ��Ƿ���ͬ�� " + (log_previous == log_new));
        //�Ƚϸ���
        System.out.println("�����Ƿ���ͬ�� " + (log_previous.getAttachment() == log_new.getAttachment()));
    }
}