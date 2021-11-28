//DesignPattern.book_java_design_pattern.oricode.observer.Observer.java
package designpattern.book.oricode.observer;

//����۲���
public interface Observer {
    public String getName();

    public void setName(String name);

    public void help(); //����֧Ԯ���ѷ���

    public void beAttacked(AllyControlCenter acc); //�������ܹ�������
}
