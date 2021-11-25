//DesignPattern.book_java_design_pattern.oricode.bridge.UnixImp.java
package DesignPattern.book_java_design_pattern.oricode.bridge;

//Unix����ϵͳʵ���࣬�䵱����ʵ����
public class UnixImp implements ImageImp {
    public void doPaint(Matrix m) {
        //����Unixϵͳ�Ļ��ƺ����������ؾ���
        System.out.print("��Unix����ϵͳ����ʾͼ��");
    }
}