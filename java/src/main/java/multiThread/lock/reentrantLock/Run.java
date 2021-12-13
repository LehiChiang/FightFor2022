package multiThread.lock.reentrantLock;

public class Run {
    public static void main(String[] args) {
        MyService service = new MyService();

        MyThread thread1 = new MyThread(service);
        MyThread thread2 = new MyThread(service);
        MyThread thread3 = new MyThread(service);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
