//DesignPattern.book_java_design_pattern.oricode.bridge.XMLUtil.java
package DesignPattern.book_java_design_pattern.oricode.bridge;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLUtil {
    //�÷������ڴ�XML�����ļ�����ȡ������������������һ��ʵ������
    public static Object getBean(String args) {
        try {
            //�����ĵ�����
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("src//designpatterns//bridge//config.xml"));
            NodeList nl = null;
            Node classNode = null;
            String cName = null;
            nl = doc.getElementsByTagName("className");

            //��ȡ��һ�����������Ľ�㣬�����������
            if (args.equals("image")) {
                classNode = nl.item(0).getFirstChild();

            }
            //��ȡ�ڶ������������Ľ�㣬������ʵ����
            else if (args.equals("os")) {
                classNode = nl.item(1).getFirstChild();
            }

            cName = classNode.getNodeValue();
            //ͨ����������ʵ�����󲢽��䷵��
            Class c = Class.forName(cName);
            Object obj = c.getDeclaredConstructor().newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}