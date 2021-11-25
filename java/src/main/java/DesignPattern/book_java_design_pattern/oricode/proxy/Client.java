//DesignPattern.book_java_design_pattern.oricode.proxy.Client.java
package DesignPattern.book_java_design_pattern.oricode.proxy;

public class Client {
    public static void main(String args[]) {
        Searcher searcher;  //��Գ����̣��ͻ�������ֱ���ʵ������ʹ�����
        searcher = (Searcher) XMLUtil.getBean();
        String result = searcher.doSearch("���", "��Ů�ľ�");
    }
}
