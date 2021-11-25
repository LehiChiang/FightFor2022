//DesignPattern.book_java_design_pattern.oricode.composite.VideoFile.java
package DesignPattern.book_java_design_pattern.oricode.composite;

public class VideoFile extends AbstractFile {
    private String name;

    public VideoFile(String name) {
        this.name = name;
    }

    public void add(AbstractFile file) {
        System.out.println("�Բ��𣬲�֧�ָ÷�����");
    }

    public void remove(AbstractFile file) {
        System.out.println("�Բ��𣬲�֧�ָ÷�����");
    }

    public AbstractFile getChild(int i) {
        System.out.println("�Բ��𣬲�֧�ָ÷�����");
        return null;
    }

    public void killVirus() {
        //ģ��ɱ��
        System.out.println("----����Ƶ�ļ�'" + name + "'����ɱ��");
    }
}