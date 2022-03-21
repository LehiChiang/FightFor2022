package multiThread.exercises.exe1;

public class MainThread {
    public static void main(String[] args) {
        SubThread sub = new SubThread();
        new Thread(() -> {
            for (int i = 0; i < 3; i++)
                sub.threadA();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++)
                sub.threadB();
        }).start();
    }
}

class SubThread {

    private boolean threadBFirst = false ;

    public synchronized void threadA() {
        while (threadBFirst) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("threadA"));
        this.notifyAll();
        threadBFirst = true;
    }

    public synchronized void threadB() {
        while (!threadBFirst) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("threadB"));
        this.notifyAll();
        threadBFirst = false;
    }
}
