package multiThread.threadCommunication.OnePOneC;

import java.util.LinkedList;
import java.util.List;

class SingleElemStack {
    private List<Integer> list = new LinkedList<>();

    synchronized public void push() {
        while (list.size() == 1) {
            System.out.println(Thread.currentThread().getName() + "---" + "list已满，push操作wait中！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int num = (int) (Math.random() * 10);
        list.add(num);
        System.out.println(Thread.currentThread().getName() + "---" + "push " + num + "!");
        this.notifyAll();
    }

    synchronized public void pop() {
        while (list.size() == 0) {
            System.out.println(Thread.currentThread().getName() + "---" + "list已空，pop操作wait中！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int num = list.get(0);
        list.remove(0);
        System.out.println(Thread.currentThread().getName() + "---" + "pop " + num + "!");
        this.notifyAll();
    }
}
