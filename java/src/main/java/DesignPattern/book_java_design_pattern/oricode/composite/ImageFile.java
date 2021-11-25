//DesignPattern.book_java_design_pattern.oricode.composite.ImageFile.java
package DesignPattern.book_java_design_pattern.oricode.composite;

public class ImageFile extends AbstractFile {
    private String name;

    public ImageFile(String name) {
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
        System.out.println("----��ͼ���ļ�'" + name + "'����ɱ��");
    }
}