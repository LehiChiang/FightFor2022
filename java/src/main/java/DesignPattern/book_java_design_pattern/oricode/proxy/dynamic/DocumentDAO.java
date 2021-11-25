//DesignPattern.book_java_design_pattern.oricode.proxy.dynamic.DocumentDAO.java
package DesignPattern.book_java_design_pattern.oricode.proxy.dynamic;

//����DocumentDAO�ࣺ��ʵ��ɫ
public class DocumentDAO implements AbstractDocumentDAO {
    public Boolean deleteDocumentById(String documentId) {
        if (documentId.equalsIgnoreCase("D001")) {
            System.out.println("ɾ��IDΪ" + documentId + "���ĵ���Ϣ�ɹ���");
            return true;
        } else {
            System.out.println("ɾ��IDΪ" + documentId + "���ĵ���Ϣʧ�ܣ�");
            return false;
        }
    }
}