//DesignPattern.book_java_design_pattern.oricode.builder.Client.java
package designpattern.book.oricode.builder;

public class Client {
    public static void main(String args[]) {
        ActorBuilder ab; //��Գ������߱��
        ab = (ActorBuilder) XMLUtil.getBean(); //�������ɾ��彨���߶���

        ActorController ac = new ActorController();
        Actor actor;
        actor = ac.construct(ab); //ͨ��ָ���ߴ��������Ľ����߶���

        String type = actor.getType();
        System.out.println(type + "����ۣ�");
        System.out.println("�Ա�" + actor.getSex());
        System.out.println("���ݣ�" + actor.getFace());
        System.out.println("��װ��" + actor.getCostume());
        System.out.println("���ͣ�" + actor.getHairstyle());
    }
}