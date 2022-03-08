//DesignPattern.book_java_design_pattern.oricode.visitor.ParttimeEmployee.java
package designpattern.book.oricode.visitor;


//��ְԱ���ࣺ����Ԫ����
public class ParttimeEmployee implements Employee {
    private String name; //Ա������
    private double hourWage; //Ա��ʱн
    private int workTime; //����ʱ��

    public ParttimeEmployee(String name, double hourWage, int workTime) {
        this.name = name;
        this.hourWage = hourWage;
        this.workTime = workTime;
    }

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHourWage() {
        return (this.hourWage);
    }

    public void setHourWage(double hourWage) {
        this.hourWage = hourWage;
    }

    public int getWorkTime() {
        return (this.workTime);
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public void accept(Department handler) {
        handler.visit(this); //���÷����ߵķ��ʷ���
    }
}