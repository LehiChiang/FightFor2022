//DesignPattern.book_java_design_pattern.oricode.templatemethod.hookmethod.DataViewer.java
package designpattern.book.oricode.templatemethod.hookmethod;

public abstract class DataViewer {
    //���󷽷�����ȡ����
    public abstract void getData();

    //���巽����ת������
    public void convertData() {
        System.out.println("������ת��ΪXML��ʽ��");
    }

    //���󷽷�����ʾ����
    public abstract void displayData();

    //���ӷ������ж��Ƿ�ΪXML��ʽ������
    public boolean isNotXMLData() {
        return true;
    }

    //ģ�巽��
    public void process() {
        getData();
        //�������XML��ʽ���������������ת��
        if (isNotXMLData()) {
            convertData();
        }
        displayData();
    }
}