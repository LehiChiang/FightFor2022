//DesignPattern.book_java_design_pattern.oricode.bridge.JPGImage.java
package DesignPattern.book_java_design_pattern.oricode.bridge;

//JPG��ʽͼ���࣬�䵱���������
public class JPGImage extends Image {
    public void parseFile(String fileName) {
        //ģ�����JPG�ļ������һ�����ؾ������m;
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + "����ʽΪJPG��");
    }
}
