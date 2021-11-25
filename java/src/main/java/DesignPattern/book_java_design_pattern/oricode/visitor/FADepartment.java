//DesignPattern.book_java_design_pattern.oricode.visitor.FADepartment.java
package DesignPattern.book_java_design_pattern.oricode.visitor;

//�����ࣺ�����������
public class FADepartment extends Department {
    //ʵ�ֲ��񲿶�ȫְԱ���ķ���
    public void visit(FulltimeEmployee employee) {
        int workTime = employee.getWorkTime();
        double weekWage = employee.getWeeklyWage();
        if (workTime > 40) {
            weekWage = weekWage + (workTime - 40) * 100;
        } else if (workTime < 40) {
            weekWage = weekWage - (40 - workTime) * 80;
            if (weekWage < 0) {
                weekWage = 0;
            }
        }
        System.out.println("��ʽԱ��" + employee.getName() + "ʵ�ʹ���Ϊ��" + weekWage + "Ԫ��");
    }

    //ʵ�ֲ��񲿶Լ�ְԱ���ķ���
    public void visit(ParttimeEmployee employee) {
        int workTime = employee.getWorkTime();
        double hourWage = employee.getHourWage();
        System.out.println("��ʱ��" + employee.getName() + "ʵ�ʹ���Ϊ��" + workTime * hourWage + "Ԫ��");
    }
}
