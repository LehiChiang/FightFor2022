package java核心技术卷一.chap6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class code_6_3_1 {
    public static void main(String[] args) {
        var clock = new TalkingClock(1000, true);
        clock.start();
        JOptionPane.showMessageDialog(null, "Quit?");
        System.exit(0);
    }
}

class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        class TimePrinter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getWhen());
                if (TalkingClock.this.beep) Toolkit.getDefaultToolkit().beep();
            }
        }

        var listener = new TimePrinter();
        var time = new Timer(interval, listener);
        time.start();
    }
}
