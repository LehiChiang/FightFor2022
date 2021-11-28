//DesignPattern.book_java_design_pattern.oricode.proxy.RealSearcher.java
package designpattern.book.oricode.proxy;

//�����ѯ�ࣺ��ʵ������
public class RealSearcher implements Searcher {
    //ģ���ѯ������Ϣ
    public String doSearch(String userId, String keyword) {
        System.out.println("�û�'" + userId + "'ʹ�ùؼ���'" + keyword + "'��ѯ������Ϣ��");
        return "���ؾ�������";
    }
}
