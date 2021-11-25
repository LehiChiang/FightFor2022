//DesignPattern.book_java_design_pattern.oricode.simplefactory.LineChart.java
package DesignPattern.book_java_design_pattern.oricode.simplefactory;

//����ͼ�࣬�䵱�����Ʒ��
public class LineChart implements Chart {
    public LineChart() {
        System.out.println("��������ͼ��");
    }

    public void display() {
        System.out.println("��ʾ����ͼ��");
    }
}