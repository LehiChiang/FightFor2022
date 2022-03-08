//DesignPattern.book_java_design_pattern.oricode.bridge.JPGImage.java
package designpattern.book.oricode.bridge;

import java.awt.*;

//JPG��ʽͼ���࣬�䵱���������
public class JPGImage extends Image {
    public void parseFile(String fileName) {
        //ģ�����JPG�ļ������һ�����ؾ������m;
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + "����ʽΪJPG��");
    }
}
