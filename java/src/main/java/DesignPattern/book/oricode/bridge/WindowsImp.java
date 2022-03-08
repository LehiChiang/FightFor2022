//DesignPattern.book_java_design_pattern.oricode.bridge.WindowsImp.java
package designpattern.book.oricode.bridge;

//Windows����ϵͳʵ���࣬�䵱����ʵ����
public class WindowsImp implements ImageImp {
    public void doPaint(Matrix m) {
        //����Windowsϵͳ�Ļ��ƺ����������ؾ���
        System.out.print("��Windows����ϵͳ����ʾͼ��");
    }
}