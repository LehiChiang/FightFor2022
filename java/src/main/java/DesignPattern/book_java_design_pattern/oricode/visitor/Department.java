//DesignPattern.book_java_design_pattern.oricode.visitor.Department.java
package DesignPattern.book_java_design_pattern.oricode.visitor;

//�����ࣺ�����������
public abstract class Department {
    //����һ�����صķ��ʷ��������ڷ��ʲ�ͬ���͵ľ���Ԫ��
    public abstract void visit(FulltimeEmployee employee);

    public abstract void visit(ParttimeEmployee employee);
}