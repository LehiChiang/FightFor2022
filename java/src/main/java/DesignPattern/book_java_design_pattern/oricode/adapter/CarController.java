//DesignPattern.book_java_design_pattern.oricode.adapter.CarController.java
package DesignPattern.book_java_design_pattern.oricode.adapter;

//���������࣬�䵱Ŀ�������
public abstract class CarController {
    public void move() {
        System.out.println("��������ƶ���");
    }

    public abstract void phonate(); //��������

    public abstract void twinkle(); //�ƹ���˸
}