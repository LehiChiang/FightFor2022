//DesignPattern.book_java_design_pattern.oricode.simplefactory.Client.java
package DesignPattern.book_java_design_pattern.oricode.simplefactory;

public class Client {
    public static void main(String args[]) {
        Chart chart;
        //chart = ChartFactory.getChart("histogram"); //ͨ����̬��������������Ʒ

        String type = XMLUtil.getChartType(); //��ȡ�����ļ��еĲ���
        chart = ChartFactory.getChart(type);  //������Ʒ����

        chart.display();
    }
}
