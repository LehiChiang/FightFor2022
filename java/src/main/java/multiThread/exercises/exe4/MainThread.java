package multiThread.exercises.exe4;

import java.util.concurrent.*;

/**
 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
 */
public class MainThread {
    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "---" + System.currentTimeMillis()), "t1");
//        Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "---" + System.currentTimeMillis()), "t2");
//        Thread t3 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "---" + System.currentTimeMillis()), "t3");
//        t1.start();
//        t1.join();
//        t2.start();
//        t2.join();
//        t3.start();

        int taskCnt = 5;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                1,
                1000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(taskCnt));
        for (int i = 0; i < taskCnt; i++) {
            int finalI = i;
            executor.execute(() -> System.out.println("Task: " + (finalI + 1)));
        }
        System.out.println("hello");
        executor.shutdown();
    }
}

