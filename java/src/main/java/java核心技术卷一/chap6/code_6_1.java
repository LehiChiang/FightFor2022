package java核心技术卷一.chap6;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class code_6_1 {
    public static void main(String[] args) {
        var printer = new TimePrinter();
        var timer = new Timer(1000, printer);
        timer.start();
    }
}

class TimePrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getWhen());
    }
}