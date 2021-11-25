//DesignPattern.book_java_design_pattern.oricode.abstractfactory.Client.java
package DesignPattern.book_java_design_pattern.oricode.abstractfactory;

public class Client {
    public static void main(String args[]) {
        //??ó??????
        SkinFactory factory;
        Button bt;
        TextField tf;
        ComboBox cb;
        factory = (SkinFactory) XMLUtil.getBean();
        bt = factory.createButton();
        tf = factory.createTextField();
        cb = factory.createComboBox();
        bt.display();
        tf.display();
        cb.display();
    }
}