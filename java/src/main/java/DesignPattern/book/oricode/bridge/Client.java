//DesignPattern.book_java_design_pattern.oricode.bridge.Client.java
package designpattern.book.oricode.bridge;

import java.awt.*;

//�ͻ��˲�����
public class Client {
    public static void main(String args[]) {
        Image image;
        ImageImp imp;
        image = (Image) XMLUtil.getBean("image");
        imp = (ImageImp) XMLUtil.getBean("os");
        image.setImageImp(imp);
        image.parseFile("С��Ů");
    }
}