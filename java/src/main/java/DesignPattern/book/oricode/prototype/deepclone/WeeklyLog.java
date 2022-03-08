//DesignPattern.book_java_design_pattern.oricode.prototype.deepclone.WeeklyLog.java
package designpattern.book.oricode.prototype.deepclone;

import java.io.*;

public class WeeklyLog implements Serializable {
    private Attachment attachment;
    private String name;
    private String date;
    private String content;

    public Attachment getAttachment() {
        return (this.attachment);
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return (this.date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return (this.content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    //ʹ�����л�����ʵ�����¡
    public WeeklyLog deepClone() throws IOException, ClassNotFoundException, OptionalDataException {
        //������д������
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);

        //�����������ȡ��
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (WeeklyLog) ois.readObject();
    }
}