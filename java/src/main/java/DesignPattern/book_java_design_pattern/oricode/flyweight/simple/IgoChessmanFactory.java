//DesignPattern.book_java_design_pattern.oricode.flyweight.simple.IgoChessmanFactory.java
package DesignPattern.book_java_design_pattern.oricode.flyweight.simple;

import java.util.Hashtable;

//Χ�����ӹ����ࣺ��Ԫ�����࣬ʹ�õ���ģʽ�������
public class IgoChessmanFactory {
    private static IgoChessmanFactory instance = new IgoChessmanFactory();
    private static Hashtable ht; //ʹ��Hashtable���洢��Ԫ���󣬳䵱��Ԫ��

    private IgoChessmanFactory() {
        ht = new Hashtable();
        IgoChessman black, white;
        black = new BlackIgoChessman();
        ht.put("b", black);
        white = new WhiteIgoChessman();
        ht.put("w", white);
    }

    //������Ԫ�������Ψһʵ��
    public static IgoChessmanFactory getInstance() {
        return instance;
    }

    //ͨ��key����ȡ�洢��Hashtable�е���Ԫ����
    public static IgoChessman getIgoChessman(String color) {
        return (IgoChessman) ht.get(color);
    }
}