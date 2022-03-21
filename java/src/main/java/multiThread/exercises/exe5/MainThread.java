package multiThread.exercises.exe5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个任务，分别输出A,B,C 使用多线程交替输出, 使用ReentrantLock关键字
 */
public class MainThread {
    public static void main(String[] args) {
        int printCount = 10;
        PrintService printService = new PrintService();
        new Thread(() -> {
            for (int i = 0; i < printCount; i++)
                printService.printA();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < printCount; i++)
                printService.printB();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < printCount; i++)
                printService.printC();
        }).start();
    }
}

class PrintService {

    private int flag = 1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA() {
        try {
            lock.lock();
            if (flag != 1)
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            System.out.print("A");
            flag = 2;
            conditionB.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        try {
            lock.lock();
            if (flag != 2)
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            System.out.print("B");
            flag = 3;
            conditionC.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        try {
            lock.lock();
            if (flag != 3)
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            System.out.println("C");
            Thread.sleep(1000);
            flag = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
