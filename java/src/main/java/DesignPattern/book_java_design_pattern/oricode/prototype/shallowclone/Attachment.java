//DesignPattern.book_java_design_pattern.oricode.prototype.shallowclone.Attachment.java
package DesignPattern.book_java_design_pattern.oricode.prototype.shallowclone;

public class Attachment {
    private String name; //������

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void download() {
        System.out.println("���ظ������ļ���Ϊ" + name);
    }
}