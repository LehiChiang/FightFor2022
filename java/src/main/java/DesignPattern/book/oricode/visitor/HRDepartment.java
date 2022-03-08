//DesignPattern.book_java_design_pattern.oricode.visitor.HRDepartment.java
package designpattern.book.oricode.visitor;

//������Դ���ࣺ�����������
public class HRDepartment extends Department {
    //ʵ��������Դ����ȫְԱ���ķ���
    public void visit(FulltimeEmployee employee) {
        int workTime = employee.getWorkTime();
        System.out.println("��ʽԱ��" + employee.getName() + "ʵ�ʹ���ʱ��Ϊ��" + workTime + "Сʱ��");
        if (workTime > 40) {
            System.out.println("��ʽԱ��" + employee.getName() + "�Ӱ�ʱ��Ϊ��" + (workTime - 40) + "Сʱ��");
        } else if (workTime < 40) {
            System.out.println("��ʽԱ��" + employee.getName() + "���ʱ��Ϊ��" + (40 - workTime) + "Сʱ��");
        }
    }

    //ʵ��������Դ���Լ�ְԱ���ķ���
    public void visit(ParttimeEmployee employee) {
        int workTime = employee.getWorkTime();
        System.out.println("��ʱ��" + employee.getName() + "ʵ�ʹ���ʱ��Ϊ��" + workTime + "Сʱ��");
    }
}
