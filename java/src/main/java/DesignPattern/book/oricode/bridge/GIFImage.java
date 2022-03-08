//DesignPattern.book_java_design_pattern.oricode.bridge.GIFImage.java
package designpattern.book.oricode.bridge;

import java.awt.*;

//GIF��ʽͼ���࣬�䵱���������
public class GIFImage extends Image {
    public void parseFile(String fileName) {
        //ģ�����GIF�ļ������һ�����ؾ������m;
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + "����ʽΪGIF��");
    }
}