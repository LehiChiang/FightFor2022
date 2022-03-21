package multiThread.exercises.exe7;

import javax.xml.transform.dom.DOMResult;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 启动3个线程打印递减数字，范围是：30~1。要求数字不能重复，每个线程打印一个数字后，立刻进入睡眠状态，睡眠时间300毫秒。（模拟多线程售票）
 */
public class MainThread {
    public static void main(String[] args) {
        SellTicketService service = new SellTicketService();
        new Thread(() -> {
            for (int i = 0; i < 10; i++)
                service.buyA();
        }, String.format("Thread %d: ", 1)).start();
        new Thread(() -> {

            for (int i = 0; i < 10; i++)
                service.buyB();
        }, String.format("Thread %d: ", 2)).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++)
                service.buyC();
        }, String.format("Thread %d: ", 3)).start();
    }
}

class SellTicketService {

    private volatile int ticketsNum = 30;
    private int flag = 1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void buyA() {
        try {
            lock.lock();
            if (flag != 1) {
                conditionA.await();
            }
            ticketsNum--;
            flag = 2;
            System.out.println(Thread.currentThread().getName() + String.format("买了一张票，还剩下 %d 张", ticketsNum));
            Thread.sleep(300);
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void buyB() {
        try {
            lock.lock();
            if (flag != 2) {
                conditionB.await();
            }
            ticketsNum--;
            flag = 3;
            System.out.println(Thread.currentThread().getName() + String.format("买了一张票，还剩下 %d 张", ticketsNum));
            Thread.sleep(300);
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void buyC() {
        try {
            lock.lock();
            if (flag != 3) {
                conditionC.await();
            }
            ticketsNum--;
            flag = 1;
            System.out.println(Thread.currentThread().getName() + String.format("买了一张票，还剩下 %d 张", ticketsNum));
            Thread.sleep(300);
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
