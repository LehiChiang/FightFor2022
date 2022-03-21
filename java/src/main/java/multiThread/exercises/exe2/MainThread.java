package multiThread.exercises.exe2;

/**
 * 设计四个线程,其中两个线程每次对变量i加1,另外两个线程每次对i减1.
 */
public class MainThread {
    public static void main(String[] args) {
        Operation operation = new Operation();
        new Thread(() -> operation.add(), "t1").start();
        new Thread(() -> operation.add(), "t2").start();
        new Thread(() -> operation.sub(), "t3").start();
        new Thread(() -> operation.sub(), "t4").start();
    }
}

class Operation {
    private volatile int num = 0;

    public synchronized void add() {
        num++;
        System.out.println(Thread.currentThread().getName() + "add " + num);
    }

    public synchronized void sub() {
        num--;
        System.out.println(Thread.currentThread().getName() + "sub " + num);
    }
}
