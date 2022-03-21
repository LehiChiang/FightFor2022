package multiThread.exercises.exe3;

/**
 * 三个任务，分别输出A,B,C 使用多线程交替输出, 使用synchronized关键字
 */
public class MainThread {
    public static void main(String[] args) {
        ThreadTask threadTask = new ThreadTask();
        new Thread(() -> {
            for (int i = 0; i < 5; i++)
                threadTask.printA();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++)
                threadTask.printB();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++)
                threadTask.printC();
        }).start();
    }
}

class ThreadTask {
    private int flag = 1;

    public synchronized void printA() {
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("A");
        this.notifyAll();
        flag = 2;
    }

    public synchronized void printB() {
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("B");
        this.notifyAll();
        flag = 3;
    }

    public synchronized void printC() {
        while (flag != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("C");
        this.notifyAll();
        flag = 1;
    }
}