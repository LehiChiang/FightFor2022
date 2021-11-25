//DesignPattern.book_java_design_pattern.oricode.singleton.Client.java
package DesignPattern.book_java_design_pattern.oricode.singleton;

//�ͻ��˲�����
public class Client {
    public static void main(String args[]) {
        //����4��LoadBalancer����
        LoadBalancer balancer1, balancer2, balancer3, balancer4;
        balancer1 = LoadBalancer.getLoadBalancer();
        balancer2 = LoadBalancer.getLoadBalancer();
        balancer3 = LoadBalancer.getLoadBalancer();
        balancer4 = LoadBalancer.getLoadBalancer();

        //�жϷ��������ؾ������Ƿ���ͬ
        if (balancer1 == balancer2 && balancer2 == balancer3 && balancer3 == balancer4) {
            System.out.println("���������ؾ���������Ψһ�ԣ�");
        }

        //���ӷ�����
        balancer1.addServer("Server 1");
        balancer1.addServer("Server 2");
        balancer1.addServer("Server 3");
        balancer1.addServer("Server 4");

        //ģ��ͻ�������ķַ������������ȫΪͬһ��server�����Խ�i�ʵ��Ŵ�
        //�����Ϊ"i < 100"
        for (int i = 0; i < 10; i++) {
            String server = balancer1.getServer();
            System.out.println("�ַ��������������� " + server);
        }
    }
}