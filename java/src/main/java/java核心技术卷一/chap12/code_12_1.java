package java核心技术卷一.chap12;

class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class code_12_1 {
    public static void main(String[] args) {
        new MyThread().start();
    }
}
