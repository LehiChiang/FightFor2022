//DesignPattern.book_java_design_pattern.oricode.composite.Folder.java
package designpattern.book.oricode.composite;

import java.util.ArrayList;

public class Folder extends AbstractFile {
    //���弯��fileList�����ڴ洢AbstractFile���͵ĳ�Ա
    private ArrayList<AbstractFile> fileList = new ArrayList<AbstractFile>();
    private String name;

    public Folder(String name) {
        this.name = name;
    }

    public void add(AbstractFile file) {
        fileList.add(file);
    }

    public void remove(AbstractFile file) {
        fileList.remove(file);
    }

    public AbstractFile getChild(int i) {
        return (AbstractFile) fileList.get(i);
    }

    public void killVirus() {
        System.out.println("****���ļ���'" + name + "'����ɱ��");  //ģ��ɱ��

        //�ݹ���ó�Ա������killVirus()����
        for (Object obj : fileList) {
            ((AbstractFile) obj).killVirus();
        }
    }
}