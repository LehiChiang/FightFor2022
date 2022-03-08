//DesignPattern.book_java_design_pattern.oricode.bridge.PNGImage.java
package designpattern.book.oricode.bridge;

import java.awt.*;

//PNG��ʽͼ���࣬�䵱���������
public class PNGImage extends Image {
    public void parseFile(String fileName) {
        //ģ�����PNG�ļ������һ�����ؾ������m;
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + "����ʽΪPNG��");
    }
}