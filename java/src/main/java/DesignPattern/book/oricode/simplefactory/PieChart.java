//DesignPattern.book_java_design_pattern.oricode.simplefactory.PieChart.java
package designpattern.book.oricode.simplefactory;

//��״ͼ�࣬�䵱�����Ʒ��
public class PieChart implements Chart {
    public PieChart() {
        System.out.println("������״ͼ��");
    }

    public void display() {
        System.out.println("��ʾ��״ͼ��");
    }
}