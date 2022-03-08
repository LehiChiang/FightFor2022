//DesignPattern.book_java_design_pattern.oricode.templatemethod.hookmethod.XMLDataViewer.java
package designpattern.book.oricode.templatemethod.hookmethod;

public class XMLDataViewer extends DataViewer {
    //ʵ�ָ��෽������ȡ����
    public void getData() {
        System.out.println("��XML�ļ��л�ȡ���ݡ�");
    }

    //ʵ�ָ��෽������ʾ����
    public void displayData() {
        System.out.println("����״ͼ��ʾ���ݡ�");
    }

    //���Ǹ���Ĺ��ӷ���
    public boolean isNotXMLData() {
        return false;
    }
}