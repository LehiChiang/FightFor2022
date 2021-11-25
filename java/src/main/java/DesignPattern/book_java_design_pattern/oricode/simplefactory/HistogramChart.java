//DesignPattern.book_java_design_pattern.oricode.simplefactory.HistogramChart.java
package DesignPattern.book_java_design_pattern.oricode.simplefactory;

//��״ͼ�࣬�䵱�����Ʒ��
public class HistogramChart implements Chart {
    public HistogramChart() {
        System.out.println("������״ͼ��");
    }

    public void display() {
        System.out.println("��ʾ��״ͼ��");
    }
}