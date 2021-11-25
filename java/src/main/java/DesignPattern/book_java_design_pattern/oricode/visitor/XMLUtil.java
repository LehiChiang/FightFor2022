//DesignPattern.book_java_design_pattern.oricode.visitor.XMLUtil.java
package DesignPattern.book_java_design_pattern.oricode.visitor;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLUtil {
    //�÷������ڴ�XML�����ļ�����ȡ������������������һ��ʵ������
    public static Object getBean() {
        try {
            //����DOM�ĵ�����
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("src//designpatterns//visitor//config.xml"));

            //��ȡ�����������ı��ڵ�
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String cName = classNode.getNodeValue();

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