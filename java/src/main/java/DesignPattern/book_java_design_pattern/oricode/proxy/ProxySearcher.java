//DesignPattern.book_java_design_pattern.oricode.proxy.ProxySearcher.java
package DesignPattern.book_java_design_pattern.oricode.proxy;

//�����ѯ�ࣺ����������
public class ProxySearcher implements Searcher {
    private RealSearcher searcher = new RealSearcher(); //ά��һ������ʵ���������
    private AccessValidator validator;
    private Logger logger;

    public String doSearch(String userId, String keyword) {
        //��������֤�ɹ�����ִ�в�ѯ
        if (this.validate(userId)) {
            String result = searcher.doSearch(userId, keyword); //������ʵ�������Ĳ�ѯ����
            this.log(userId); //��¼��ѯ��־
            return result; //���ز�ѯ���
        } else {
            return null;
        }
    }

    //����������֤���󲢵�����validate()����ʵ�������֤
    public boolean validate(String userId) {
        validator = new AccessValidator();
        return validator.validate(userId);
    }

    //������־��¼���󲢵�����log()����ʵ����־��¼
    public void log(String userId) {
        logger = new Logger();
        logger.log(userId);
    }
}
