//DesignPattern.book_java_design_pattern.oricode.prototype.deepclone.Attachment.java
package designpattern.book.oricode.prototype.deepclone;

import java.io.Serializable;

public class Attachment implements Serializable {
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