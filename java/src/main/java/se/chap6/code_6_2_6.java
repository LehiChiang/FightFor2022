package se.chap6;

import javax.swing.*;
import java.awt.*;

public class code_6_2_6 {
    public static void main(String[] args) {
        repeatMessage("hello", 1000);
    }

    private static void repeatMessage(String hello, int delay) {
        new Timer(delay, event -> {
            System.out.println(event);
            Toolkit.getDefaultToolkit().beep();
        }).start();
    }
}
