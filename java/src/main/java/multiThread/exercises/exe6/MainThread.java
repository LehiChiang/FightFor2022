package multiThread.exercises.exe6;

/**
 * 练习题1：三个线程交替打印数字
 * 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5 然后是线程2打印6,7,8,9,10 然后是线程3打印11,12,13,14,15.
 * 接着再由线程1打印16,17,18,19,20....依次类推, 直到打印到60。
 *
 * 提示：定义一个同步方法，在方法中使用循环的方式输出连续的5个数字，方法执行前，可以通过标记的方式判断是否是当前线程应该执行，如果是，
 * 则执行输出逻辑，如果不是，则wait进入等待队列，在方法执行结束前，必须使用notifyAll唤醒其他所有线程。
 */
public class MainThread {
    public static void main(String[] args) {
        PrintDigitService service = new PrintDigitService();
        new Thread(() -> {
            for (int i = 0; i < 4; i++)
                service.print1();
        }, "Thread-1").start();
        new Thread(() -> {
            for (int i = 0; i < 4; i++)
                service.print2();
        }, "Thread-2").start();
        new Thread(() -> {
            for (int i = 0; i < 4; i++)
                service.print3();
        }, "Thread-3").start();
    }
}

class PrintDigitService {

    private int flag = 1;
    private int num = 1;

    public synchronized void print1() {
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName() + ": ");
        for (int i = 0; i < 5; i++) {
            System.out.print("" + num + ',');
            num++;
        }
        System.out.println();
        flag = 2;
        this.notifyAll();
    }

    public synchronized void print2() {
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName() + ": ");
        for (int i = 0; i < 5; i++) {
            System.out.print("" + num + ',');
            num++;
        }
        System.out.println();
        flag = 3;
        this.notifyAll();
    }

    public synchronized void print3() {
        while (flag != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName() + ": ");
        for (int i = 0; i < 5; i++) {
            System.out.print("" + num + ',');
            num++;
        }
        System.out.println();
        flag = 1;
        this.notifyAll();
    }
}
