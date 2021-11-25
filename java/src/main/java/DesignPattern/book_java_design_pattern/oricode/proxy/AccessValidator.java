//DesignPattern.book_java_design_pattern.oricode.proxy.AccessValidator.java
package DesignPattern.book_java_design_pattern.oricode.proxy;

//�����֤�ࣺҵ����
public class AccessValidator {
    //ģ��ʵ�ֵ�¼��֤
    public boolean validate(String userId) {
        System.out.println("�����ݿ�����֤�û�'" + userId + "'�Ƿ��ǺϷ��û���");
        if (userId.equalsIgnoreCase("���")) {
            System.out.println("'" + userId + "'��¼�ɹ���");
            return true;
        } else {
            System.out.println("'" + userId + "'��¼ʧ�ܣ�");
            return false;
        }
    }
}
