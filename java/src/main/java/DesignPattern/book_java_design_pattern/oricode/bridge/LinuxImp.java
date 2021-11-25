//DesignPattern.book_java_design_pattern.oricode.bridge.LinuxImp.java
package DesignPattern.book_java_design_pattern.oricode.bridge;

//Linux����ϵͳʵ���࣬�䵱����ʵ����
public class LinuxImp implements ImageImp {
    public void doPaint(Matrix m) {
        //����Linuxϵͳ�Ļ��ƺ����������ؾ���
        System.out.print("��Linux����ϵͳ����ʾͼ��");
    }
}