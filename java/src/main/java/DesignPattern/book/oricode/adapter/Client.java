//DesignPattern.book_java_design_pattern.oricode.adapter.Client.java
package designpattern.book.oricode.adapter;

public class Client {
    public static void main(String args[]) {
        CarController car;
        car = (CarController) XMLUtil.getBean();
        car.move();
        car.phonate();
        car.twinkle();
    }
}